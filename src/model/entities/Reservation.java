package model.entities;

//region IMPORTS

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


//endregion

public class Reservation  {

    //region ATRIBUTOS
    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;
    //endregion

    //region ETC
    //Formato de data Date

    //Ele precisa ser estatico para que nao seja instanciado um sdf para cada instancia da classe Reservation
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //endregion

    //region CONSTRUTORES

    //default
    public Reservation(){
    }

    // Com argumentos                                                    // Pode lançar enxceções
    public Reservation(Integer roomNumber ,Date checkIn ,Date checkOut) throws DomainException{
        // Tratando a possive exceção no inicio do construtor|Método isso é programação defensiva
        if(!checkOut.after(checkIn)){
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    //endregion

    //region GETTERS AND SETTERS

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }


    public Date getCheckOut() {
        return checkOut;
    }

    //endregion

    //region MÉTODOS

    // Método pegar a duração

    // Tipo long inteiro mais longo
    public long duration(){
        // Pegando as datas de saida e entrada em milisegundos
        // E calculando a diferença em diff
        long diff = checkOut.getTime() - checkIn.getTime();
        // Usando Classe TimeUnit para converter diff de milisegundos para horas
        // Retornando a diferença em dias
        return TimeUnit.DAYS.convert(diff ,TimeUnit.MILLISECONDS);
    }

    // Método para atualizar as datas                   // Meu método pode lançar exceções
    public void updateDates(Date checkIn ,Date checkOut) throws DomainException {

        //Checando erros
        Date now = new Date();
        if(checkIn.before(now) || checkOut.before(now)){
            // Lançando exceção
            throw new DomainException("Reservation dates for update must be future dates");
        }
        if(!checkOut.after(checkIn)){
            throw new DomainException("Check-out date must be after check-in date");
        }


        // Atributo de classe recebendo argumento de método
        this.checkIn = checkIn;
        this.checkOut = checkOut;



    }


    @Override
    public String toString() {
        return
                "Room= " + roomNumber +
                ", Check-in= " + sdf.format(checkIn) +
                ", Check-out= " + sdf.format(checkOut)
                +", "
                +duration()
                        +" nights";
    }
    //endregion

}
