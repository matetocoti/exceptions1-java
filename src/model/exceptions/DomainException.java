package model.exceptions;


//Meu pacote/Classe de exceções personalizadas

                                //Com exception o compilador vai obrigar você a tratar

                                // Como a RunTimeException é também do tipo Exception,
                                // se você tratar as Exceptions, você estará tratando também a RunTimeException

                                //com RuntimeExeption você não é obrigado a tratar
                                //acontecem durante a execução do programa
public class DomainException extends Exception{

    public DomainException(String msg){
        super(msg);
    }

}
