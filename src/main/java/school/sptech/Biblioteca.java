package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Livro> livros;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarLivro(Livro livro){
        if(livro == null || livro.getTitulo() == null || livro.getTitulo().isBlank() || livro.getAutor() == null || livro.getAutor().isBlank() || livro.getDataPublicacao() == null){
            throw new ArgumentoInvalidoException("O livro que você quer adicionar está incorreto");
        }

        livros.add(livro);
    }

    public void removerLivroPorTitulo(String titulo){
        if(titulo == null || titulo.isBlank()){
            throw new ArgumentoInvalidoException("O titulo está incorreto");
        }

        for (int i = 0; i < livros.size(); i++) {
            if(livros.get(i).getTitulo().equalsIgnoreCase(titulo)){
                livros.remove(i);
                return;
            }
        }

        throw new LivroNaoEncontradoException("O livro que você quer remover não foi encontrado");
    }

    public Livro buscarLivroPorTitulo(String titulo){
        if(titulo == null || titulo.isBlank()){
            throw new ArgumentoInvalidoException("O titulo está incorreto");
        }

        for (int i = 0; i < livros.size(); i++) {
            if(livros.get(i).getTitulo().equalsIgnoreCase(titulo)){
                return livros.get(i);
            }
        }

        throw new LivroNaoEncontradoException("O livro que você procura não foi encontrado");
    }

    public Integer contarLivros(){
        return livros.size();
    }

    public List<Livro> obterLivrosAteAno(Integer ano){

        List<Livro> resposta = new ArrayList<>();

        for (int i = 0; i < livros.size(); i++) {
            if(livros.get(i).getDataPublicacao().getYear() <= ano){
                resposta.add(livros.get(i));
            }
        }

        return resposta;
    }

    public List<Livro> retornarTopCincoLivros(){

        List<Livro> maiores = new ArrayList<>();
        Double maior = 0.0;

        for (int i = 0; i < livros.size(); i++) {
            if(livros.get(i).calcularMediaAvaliacoes() >= maior){
                maior = livros.get(i).calcularMediaAvaliacoes();
                maiores.addFirst(livros.get(i));
            } else {
                maiores.add(livros.get(i));
            }
        }

        if(maiores.size() >= 5){
            List<Livro> cincoMaiores = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                cincoMaiores.add(maiores.get(i));
            }

            return cincoMaiores;
        } else {
            return maiores;
        }
    }
}

