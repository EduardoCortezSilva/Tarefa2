package springboot.crud.produto.service;

import java.util.List;
import org.springframework.stereotype.Service;
import springboot.crud.produto.domain.Produto;
import springboot.crud.produto.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {

        this.produtoRepository = produtoRepository;
    }

    public Produto getProduto(Integer id) {
        return produtoRepository.findProdutoById(id);
    }

    public List<Produto> listProdutos() {
        return produtoRepository.findAll();
    }

    public void removerProduto(int IdProduto) {
        produtoRepository.deleteById(IdProduto);
    }

    public void alterarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public void inserirProduto(Produto produto) {
        produtoRepository.save(produto);
    }

}
