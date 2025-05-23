package com.algaworks.junit.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoCompraTest {
    private Cliente cliente = new Cliente(1l, "Pedro");
    private CarrinhoCompra carrinhoCompra;

    @BeforeEach
    void beforeEach() {
        Produto produto1 = new Produto(1L, "Banana", "Fruta amarela", BigDecimal.ONE);
        Produto produto2 = new Produto(2L, "Maçã", "Fruta vermelha", BigDecimal.TEN);

        ItemCarrinhoCompra item1 = new ItemCarrinhoCompra(produto1, 2);
        ItemCarrinhoCompra item2 = new ItemCarrinhoCompra(produto2, 1);

        List<ItemCarrinhoCompra> itemCarrinhoCompras = new ArrayList<>(Arrays.asList(item1, item2));

        carrinhoCompra = new CarrinhoCompra(cliente, itemCarrinhoCompras);
    }

    @Test
    @DisplayName("Deve retornar a lista de itens")
    void deveRetornarItens() {
        var retorno = carrinhoCompra.getItens();

        var itemCarrinhoCompraRetorno = retorno.get(0);
        var produtoRetorno = itemCarrinhoCompraRetorno.getProduto();

        var itemCarrinhoCompraRetorno2 = retorno.get(1);
        var produtoRetorno2 = itemCarrinhoCompraRetorno2.getProduto();

        assertAll(
                () -> assertEquals("Banana", produtoRetorno.getNome()),
                () -> assertEquals("Maçã", produtoRetorno2.getNome())
        );
    }

    @Nested
    @DisplayName("Testes com adição de um produto")
    class TestesComAdicao {
        private Produto novoProduto;

        @Nested
        @DisplayName("Deve adicionar item")
        class DeveAdicionarItem {
            @BeforeEach
            void beforeEach() {
                novoProduto = new Produto(3L, "Abacaxi", "Casa do Bob Esponja", new BigDecimal(15.0));
            }

            @Test
            @DisplayName("Deve adicionar item")
            void deveAdicionarItem() {
                carrinhoCompra.adicionarProduto(novoProduto, 5);

                var retorno = carrinhoCompra.getItens();
                var item = retorno.get(retorno.size() - 1);

                assertEquals(novoProduto, item.getProduto());
            }
        }

        @Nested
        @DisplayName("Deve adicionar a quantidade")
        class DeveAdicionarQuantidade {
            @BeforeEach
            void beforeEach() {
                var retorno = carrinhoCompra.getItens();
                var item = retorno.get(0);
                novoProduto = item.getProduto();
            }

            @Test
            @DisplayName("Deve adicionar quantidade")
            void deveAdicionarQuantidade() {
                carrinhoCompra.adicionarProduto(novoProduto, 1);

                var retorno = carrinhoCompra.getItens();
                var item = retorno.get(0);

                assertEquals(3, item.getQuantidade());
            }
        }

        @Nested
        @DisplayName("Deve falhar")
        class DeveFalhar {
            @Nested
            @DisplayName("Deve falhar por produto nulo")
            class DeveFalharPorProdutoNulo {
                @BeforeEach
                void beforeEach() {
                    novoProduto = null;
                }

                @Test
                @DisplayName("Deve falhar ao adicionar um produto nulo")
                void deveFalharComProdutoNulo() {
                    Executable executable = () -> carrinhoCompra.adicionarProduto(novoProduto, 1);
                    NullPointerException nullPointerException = assertThrows(NullPointerException.class, executable);

                    assertEquals("Produto não pode ser nulo", nullPointerException.getMessage());
                }
            }

            @Nested
            @DisplayName("Deve falhar por quantidade menor que 1")
            class DeeveFalharPorQuantidade {
                @BeforeEach
                void beforeEach() {
                    novoProduto = new Produto(3L, "Abacaxi", "Casa do Bob Esponja", new BigDecimal(15.0));
                }

                @ParameterizedTest
                @ValueSource(ints = {0, -1})
                @DisplayName("Deve falhar por quantidade menor que 1")
                void deveFalharPorQuantidadeMenor1(int valores) {
                    Executable executable = () -> carrinhoCompra.adicionarProduto(novoProduto, valores);
                    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);

                    assertEquals("Quantidade não pode ser menor que 1", illegalArgumentException.getMessage());
                }
            }
        }
    }

    @Nested
    @DisplayName("Testes de exclução")
    class TestesDeExclusão {
        Produto produtoASerExcluido;

        @Nested
        @DisplayName("Deve excluir produto")
        class DeveExcluirProduto{
            @BeforeEach
            void beforeEach() {
                produtoASerExcluido = new Produto(1L, "Banana", "Fruta amarela", BigDecimal.ONE);
            }

            @Test
            @DisplayName("Deve excluir produto")
            void deveExcluirProduto() {
                carrinhoCompra.removerProduto(produtoASerExcluido);

                boolean isProdutoExcluido = true;
                var retorno = carrinhoCompra.getItens();
                for (ItemCarrinhoCompra item : retorno) {
                    if (item.getProduto().getId().equals(produtoASerExcluido.getId()) || item.getProduto().getNome().equals(produtoASerExcluido.getNome())) {
                        isProdutoExcluido = false;
                    }
                }

                assertTrue(isProdutoExcluido);
            }
        }

        @Nested
        @DisplayName("Não deve excluir produto")
        class NaoDeveExcluirProduto {
            @Nested
            @DisplayName("Não deve excluir por produto nulo")
            class NaoDeveExcluirPorProdutoNulo {
                @BeforeEach
                void beforeEach() {
                    produtoASerExcluido = null;
                }

                @Test
                @DisplayName("Deve falhar por produto nulo")
                void deveFalharPorProdutoNulo() {
                    Executable executable = () -> carrinhoCompra.removerProduto(produtoASerExcluido);
                    NullPointerException nullPointerException = assertThrows(NullPointerException.class, executable);

                    assertEquals("Produto não pode ser nulo", nullPointerException.getMessage());
                }
            }

            @Nested
            @DisplayName("Deve falhar por produto não constar no carrinho")
            class DeveFalharPorProdutoNaoConstarNoCarrinho{
                @BeforeEach
                void beforeEach() {
                    produtoASerExcluido = new Produto(3L, "Abacaxi", "Casa do Bob Esponja", new BigDecimal(15.0));
                }

                @Test
                @DisplayName("Não deve excluir por produto não constar no carrinho")
                void deveFalharPorProdutoNaoConstarNoCarrinho() {
                    Executable executable = () -> carrinhoCompra.removerProduto(produtoASerExcluido);
                    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);

                    assertEquals("Produto não consta no carrinho", illegalArgumentException.getMessage());
                }

            }
        }
    }

    @Nested
    @DisplayName("Testes de aumentar em um a quantidade de um produto")
    class TestesComAdicaoEmUmDeUmProduto {
        Produto produtoASerAumentado;

        @Nested
        @DisplayName("Deve aumentar quantidade em 1 produto")
        class DeveAdicionarQuantidadeEmUm{
            @BeforeEach
            void beforeEach() {
                produtoASerAumentado = new Produto(1L, "Banana", "Fruta amarela", BigDecimal.ONE);
            }

            @Test
            @DisplayName("Deve aumentar quantidade produto")
            void deveAumentarQuantidadeProduto() {
                carrinhoCompra.aumentarQuantidadeProduto(produtoASerAumentado);

                var retorno = carrinhoCompra.getItens();
                var item = retorno.get(0);

                assertEquals(item.getQuantidade(), carrinhoCompra.getItens().get(0).getQuantidade());
            }
        }

        @Nested
        @DisplayName("Não deve aumentar quantidade em um do produto")
        class NaoDeveAumentarQuantidade {
            @Nested
            @DisplayName("Não deve aumentar quantidade por produto nulo")
            class NaoDeveAumentarQuantidadePorProdutoNulo {
                @BeforeEach
                void beforeEach() {
                    produtoASerAumentado = null;
                }

                @Test
                @DisplayName("Deve falhar por produto nulo")
                void deveFalharPorProdutoNulo() {
                    Executable executable = () -> carrinhoCompra.aumentarQuantidadeProduto(produtoASerAumentado);
                    NullPointerException nullPointerException = assertThrows(NullPointerException.class, executable);

                    assertEquals("Produto não pode ser nulo", nullPointerException.getMessage());
                }
            }

            @Nested
            @DisplayName("Deve falhar por produto não constar no carrinho")
            class DeveFalharPorProdutoNaoConstarNoCarrinho{
                @BeforeEach
                void beforeEach() {
                    produtoASerAumentado = new Produto(3L, "Abacaxi", "Casa do Bob Esponja", new BigDecimal(15.0));
                }

                @Test
                @DisplayName("Não deve adicionar quantidade em um por produto não constar no carrinho")
                void deveFalharPorProdutoNaoConstarNoCarrinho() {
                    Executable executable = () -> carrinhoCompra.aumentarQuantidadeProduto(produtoASerAumentado);
                    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);

                    assertEquals("Produto não consta no carrinho", illegalArgumentException.getMessage());
                }

            }
        }
    }

    @Nested
    @DisplayName("Testes de diminuir em um a quantidade de um produto")
    class TestesComDiminuicaoEmUmDeUmProduto {
        Produto produtoASerDiminuido;

        @Nested
        @DisplayName("Deve diminuir quantidade em 1 produto")
        class DeveADiminuirQuantidadeEmUm{
            @BeforeEach
            void beforeEach() {
                produtoASerDiminuido = new Produto(1L, "Banana", "Fruta amarela", BigDecimal.ONE);
            }

            @Test
            @DisplayName("Deve diminuir quantidade produto")
            void deveAumentarQuantidadeProduto() {
                carrinhoCompra.aumentarQuantidadeProduto(produtoASerDiminuido);

                var retorno = carrinhoCompra.getItens();
                var item = retorno.get(0);

                assertEquals(item.getQuantidade(), carrinhoCompra.getItens().get(0).getQuantidade());
            }
        }

        @Nested
        @DisplayName("Deve excluir produto com quantidade 1")
        class DeveExcluirProduto {
            @BeforeEach
            void beforeEach() {
                produtoASerDiminuido = new Produto(2L, "Maçã", "Fruta vermelha", BigDecimal.TEN);
            }

            @Test
            @DisplayName("Deve excluir produto")
            void deveExcluirProduto() {
                carrinhoCompra.diminuirQuantidadeProduto(produtoASerDiminuido);

                assertEquals(1, carrinhoCompra.getItens().size());
            }
        }

        @Nested
        @DisplayName("Não deve diminuir quantidade em um do produto")
        class NaoDeveDiminuirQuantidade {
            @Nested
            @DisplayName("Não deve diminuir quantidade por produto nulo")
            class NaoDeveDiminuirQuantidadePorProdutoNulo {
                @BeforeEach
                void beforeEach() {
                    produtoASerDiminuido = null;
                }

                @Test
                @DisplayName("Deve falhar por produto nulo")
                void deveFalharPorProdutoNulo() {
                    Executable executable = () -> carrinhoCompra.diminuirQuantidadeProduto(produtoASerDiminuido);
                    NullPointerException nullPointerException = assertThrows(NullPointerException.class, executable);

                    assertEquals("Produto não pode ser nulo", nullPointerException.getMessage());
                }
            }

            @Nested
            @DisplayName("Deve falhar por produto não constar no carrinho")
            class DeveFalharPorProdutoNaoConstarNoCarrinho{
                @BeforeEach
                void beforeEach() {
                    produtoASerDiminuido = new Produto(3L, "Abacaxi", "Casa do Bob Esponja", new BigDecimal(15.0));
                }

                @Test
                @DisplayName("Não deve diminuir quantidade em um por produto não constar no carrinho")
                void deveFalharPorProdutoNaoConstarNoCarrinho() {
                    Executable executable = () -> carrinhoCompra.diminuirQuantidadeProduto(produtoASerDiminuido);
                    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);

                    assertEquals("Produto não consta no carrinho", illegalArgumentException.getMessage());
                }

            }
        }
    }

    @Nested
    @DisplayName("Testes com valor total do carrinho")
    class TestesComValorTotal {
        @Test
        @DisplayName("Deve retornar o valor total do carrinho")
        void deveRetornarValorTotalDoCarrinho() {
            var retorno = carrinhoCompra.getValorTotal();

            assertEquals(new BigDecimal(12.0), retorno);
        }
    }

    @Nested
    @DisplayName("Testes com quantidade total do carrinho")
    class TestesComQuantidadeTotal {
        @Test
        @DisplayName("Deve retornar a quantidade total do carrinho")
        void deveRetornarQuantidadeTotal() {
            var retorno = carrinhoCompra.getQuantidadeTotalDeProdutos();

            assertEquals(3, retorno);
        }
    }

    @Nested
    @DisplayName("Testes com a limpeza do carrinho")
    class TestesComLimpezaCarrinho {
        @Test
        @DisplayName("Deve esvaziar o carrinho")
        void deveEsvaziarCarrinho() {
            carrinhoCompra.esvaziar();

            assertEquals(0, carrinhoCompra.getItens().size());
        }
    }
}