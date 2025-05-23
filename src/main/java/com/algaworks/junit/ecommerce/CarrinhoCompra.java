package com.algaworks.junit.ecommerce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarrinhoCompra {

	private final Cliente cliente;
	private final List<ItemCarrinhoCompra> itens;

	public CarrinhoCompra(Cliente cliente) {
		this(cliente, new ArrayList<>());
	}

	public CarrinhoCompra(Cliente cliente, List<ItemCarrinhoCompra> itens) {
		Objects.requireNonNull(cliente);
		Objects.requireNonNull(itens);
		this.cliente = cliente;
		this.itens = new ArrayList<>(itens); //Cria lista caso passem uma imutável
	}

	public List<ItemCarrinhoCompra> getItens() {
		//TODO deve retornar uma nova lista para que a antiga não seja alterada
		List<ItemCarrinhoCompra> listaAmostral = this.itens;
		return listaAmostral;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void adicionarProduto(Produto produto, int quantidade) {
		//TODO parâmetros não podem ser nulos, deve retornar uma exception
		//TODO quantidade não pode ser menor que 1
		//TODO deve incrementar a quantidade caso o produto já exista

		Objects.requireNonNull(produto, "Produto não pode ser nulo");
		if (quantidade > 0) {
			for (ItemCarrinhoCompra item : itens) {
				var produtoNaLista = item.getProduto();
				if (produtoNaLista.getId().equals(produto.getId()) && produtoNaLista.getNome().equals(produto.getNome())) {
					item.adicionarQuantidade(quantidade);
				}
			}
			itens.add(new ItemCarrinhoCompra(produto, quantidade));
		} else {
			throw new IllegalArgumentException("Quantidade não pode ser menor que 1");
		}
	}

	public void removerProduto(Produto produto) {
		//TODO parâmetro não pode ser nulo, deve retornar uma exception
		//TODO caso o produto não exista, deve retornar uma exception
		//TODO deve remover o produto independente da quantidade

		Objects.requireNonNull(produto, "Produto não pode ser nulo");
		for (ItemCarrinhoCompra item : itens) {
			var produtoNaLista = item.getProduto();
			if (produtoNaLista.getId().equals(produto.getId()) && produtoNaLista.getNome().equals(produto.getNome())) {
				itens.remove(item);
				return;
			}
		}
		throw new IllegalArgumentException("Produto não consta no carrinho");
	}

	public void aumentarQuantidadeProduto(Produto produto) {
		//TODO parâmetro não pode ser nulo, deve retornar uma exception
		//TODO caso o produto não exista, deve retornar uma exception
		//TODO deve aumentar em um quantidade do produto

		Objects.requireNonNull(produto, "Produto não pode ser nulo");
		for (ItemCarrinhoCompra item : itens) {
			var produtoNaLista = item.getProduto();
			if (produtoNaLista.getId().equals(produto.getId()) && produtoNaLista.getNome().equals(produto.getNome())) {
				item.adicionarQuantidade(1);
				return;
			}
		}
		throw new IllegalArgumentException("Produto não consta no carrinho");
	}

    public void diminuirQuantidadeProduto(Produto produto) {
		//TODO parâmetro não pode ser nulo, deve retornar uma exception
		//TODO caso o produto não exista, deve retornar uma exception
		//TODO deve diminuir em um quantidade do produto, caso tenha apenas um produto, deve remover da lista

		Objects.requireNonNull(produto, "Produto não pode ser nulo");
		for (ItemCarrinhoCompra item : itens) {
			var produtoNaLista = item.getProduto();
			if (produtoNaLista.getId().equals(produto.getId()) && produtoNaLista.getNome().equals(produto.getNome())) {
				if (item.getQuantidade() == 1) {
					removerProduto(produto);
				} else {
					item.subtrairQuantidade(1);
				}
				return;
			}
		}
		throw new IllegalArgumentException("Produto não consta no carrinho");
	}

    public BigDecimal getValorTotal() {
		//TODO implementar soma do valor total de todos itens

		BigDecimal valorTotal = BigDecimal.ZERO;
		for (ItemCarrinhoCompra item : itens) {
			valorTotal = valorTotal.add(item.getValorTotal());
		}
		return valorTotal;
    }

	public int getQuantidadeTotalDeProdutos() {
		//TODO retorna quantidade total de itens no carrinho
		//TODO Exemplo em um carrinho com 2 itens, com a quantidade 2 e 3 para cada item respectivamente, deve retornar 5

		int quantidadeTotalItens = 0;
		for (ItemCarrinhoCompra item : itens) {
			quantidadeTotalItens += item.getQuantidade();
		}
		return quantidadeTotalItens;
	}

	public void esvaziar() {
		//TODO deve remover todos os itens

		itens.clear();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CarrinhoCompra that = (CarrinhoCompra) o;
		return Objects.equals(itens, that.itens) && Objects.equals(cliente, that.cliente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(itens, cliente);
	}
}