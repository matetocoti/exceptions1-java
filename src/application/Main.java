package application;

//regions IMPORTS

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


import model.entities.Reservation;

//endregion

//Solução muito ruim
public class Main {
                            // Falando que o método main
                            // pode lançar|Não tratar esse tipo de Exceção
                            // Exceções do tipo Parse

                            // OBS:ou voce trata a exceção ou voce
                            // propaga com a palavra throws + nome da exceção
    public static void main(String[] args) throws ParseException {

        //region INSTANCIANDO

        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);

        //Formato|formatação de data
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

        //endregion


        //region ENTRADA E SAIDA DE DADOS

        System.out.print("Room number: ");
        int roomNumber = input.nextInt();
        input.nextLine();

        System.out.print("Check-in data(dd/MM/yyyy):");
        Date checkIn = sdf1.parse(input.next());

        System.out.print("Check-out data(dd/MM/yyyy):");
        Date checkOut = sdf1.parse(input.next());

        // Se data de saída não é depois de data de entrada então...
        if(!checkOut.after(checkIn)) {
            System.out.println("Error in reservation: Check-out date must be after check-in date");
        }
        else// Senão
        {
            //Instanciando o meu obj
            Reservation reservation = new Reservation(roomNumber ,checkIn ,checkOut);

            // Mostrando os dados da reserva
            // OBS: eu só coloco assim direto o obj pois eu fiz um @Override no toString()
            // Senão era só endereço de memoria no console
            System.out.println("Reservation: " + reservation);



            //region ATUALIZANDO DADOS

            System.out.println();

            System.out.println("Enter data to update the reservation: ");
            System.out.print("Check-in data(dd/MM/yyyy):");
            checkIn = sdf1.parse(input.next());

            System.out.print("Check-out data(dd/MM/yyyy):");
            checkOut = sdf1.parse(input.next());


            Date now = new Date();
            if(checkIn.before(now) || checkOut.before(now)){
                System.out.println("Error in reservation: Reservation dates for update must be future dates");
            }
            else if(!checkOut.after(checkIn)){
                System.out.println("Error in reservation: Check-out date must be after check-in date");
            }
            else{
                //Chamando o método para atualizar as datas
                reservation.updateDates(checkIn ,checkOut);

                System.out.println("Reservation: " + reservation);
            }

            //endregion
        }


        //endregion


        //region FINALIZANDO
        input.close();
        //endregion
    }
}