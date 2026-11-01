package springboot.crud.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.crud.produto.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("select p from Produto p where p.id = ?1")
    public Produto findProdutoById(Integer id);
}
