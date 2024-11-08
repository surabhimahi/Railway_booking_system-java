import java.util.*;
class Main
{
    // confirmed tickets
    static ArrayList<Passenger> confirmed_list = new ArrayList<>();
    static Queue <Passenger> RAC_Queue = new LinkedList<>();
    static Queue <Passenger> Waitinglist_Queue = new LinkedList<>();
    
    
        int choice,age =0, t_id;
        String berth=null;
        String name= null;
        ArrayList<Passenger> p = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("1. Book ticket");
            System.out.println("2. Cancel ticket");
            System.out.println("3. View confirmed ticket list");
            System.out.println("4. View RAC ticket list");
            System.out.println("5. View Waiting ticket list");
            System.out.println("6.Exit");
            System.out.println("Enter your input....");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    try {
                        System.out.println("Enter name");
                        name = sc.next();
                        System.out.println("Enter age");
                        age = sc.nextInt();
                        System.out.println("Enter berth");
                        berth = sc.next();
                        
                    } catch (Exception e) {
                        System.out.println("Exception raised, provide appropriate valid input");
                        // TODO: handle exception
                    }

                    Passenger.reserveTicket(new Passenger(name,age,berth), p);
                    break;
                    case 2:
                    System.out.println("Enter ticket number");
                    t_id = sc.nextInt();
                    Passenger.cancelTicket(t_id, p);
                    break;
                case 3:
                    Passenger.viewConfirmedTicket(p);
                    break;
                case 4:
                    Passenger.viewRACTicket(p);
                    break;
                case 5:
                    Passenger.viewWaitingTicket(p);
                    break;
                case 6:
                sc.close();
                    System.exit(1);
                default:
                System.out.println("Please enter valid input");
            }

        }

    }
}