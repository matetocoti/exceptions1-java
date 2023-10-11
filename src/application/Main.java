package application;

//region IMPORTS

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


import model.entities.Reservation;
import model.exceptions.DomainException;

//endregion

public class Main {
    public static void main(String[] args){

        //region INSTANCIANDO

        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);

        //Formato|formatação de data
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

        //endregion

        //region ENTRADA E SAIDA DE DADOS

        try {
            System.out.print("Room number: ");
            int roomNumber = input.nextInt();
            input.nextLine();

            System.out.print("Check-in data(dd/MM/yyyy):");
            Date checkIn = sdf1.parse(input.next());

            System.out.print("Check-out data(dd/MM/yyyy):");
            Date checkOut = sdf1.parse(input.next());


            //Instanciando o meu obj
            Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);

            // Mostrando os dados da reserva
            // OBS: eu só coloco assim direto o obj pois eu fiz um @Override no toString()
            // Senão era só endereço de memoria no console
            System.out.println("Reservation: " + reservation);


            //Atualizando dados

            System.out.println();

            System.out.println("Enter data to update the reservation: ");
            System.out.print("Check-in data(dd/MM/yyyy):");
            checkIn = sdf1.parse(input.next());

            System.out.print("Check-out data(dd/MM/yyyy):");
            checkOut = sdf1.parse(input.next());

            // Chamando o método para atualizar as datas
            // Variavel de tipo String Pois agora o retorno do método é string
            reservation.updateDates(checkIn, checkOut);

            // Se não há erros...
            // Exibindo dados
            System.out.println("Reservation: " + reservation);
        }
        // lançada quando ocorre um erro durante
        // a análise (conversão) de uma string em um tipo de dado específico,
        // como data ou número.
        catch (ParseException e){
            System.out.println("Invalid date format");
        }
        // Lançada quando um método recebe um argumento inválido.
        catch (DomainException e){           // Messagem de quando estanciei a exceção
            System.out.println("Error in reservation: " + e.getMessage());
        }
        //Qualquer exceção inesperada
        catch(RuntimeException e){
            System.out.println("Unexpected Error");
        }


        //endregion

        //region FINALIZANDO
        input.close();
        //endregion
    }
}
