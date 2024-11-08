import java.util.ArrayList;

class Passenger {
    String name;
    int age;
    String berth;
    int ticket_id;
    int seat_no;
    
    
    static int confirmed_ticket =6;
    static int RAC = 1;

    static int waiting_list =1;
    static int last_lower=1;
    static int last_middle=2;
    static int last_upper=3;
    static int ticket_count=1;
    static int lower_berth_count=0;
    static int middle_berth_count=0;
    static int upper_berth_count=0;
    

    Passenger(String name, int age, String berth){
        this.name= name;
        this.age= age;
        this.berth= berth; 
    }
    public static void reserveTicket(Passenger p, ArrayList<Passenger> passenger)
    {
        if(confirmed_ticket>0)
        {
            switch(p.berth){
                case "U": 
                if(last_upper<=6  ){
                    
                    upper_berth_count++;
                    p.seat_no = last_upper;
                    last_upper= last_upper+3;
                    confirmed_ticket--;
                    passenger.add(p);
                    Main.confirmed_list.add(p);
                    System.out.println("Your ticket is confirmed.");
                    System.out.println("Ticket number is"+ ticket_count);
                    p.ticket_id=ticket_count;
                    ticket_count= ticket_count+1;
                    break;
                }
                else{
                        System.out.println("U berth is not available... Choose another berth");
                        berthAvailability();
                        break;
                    }
                case "L": 
                if( last_lower<=6){
                    lower_berth_count++;
                    p.seat_no = last_lower;
                    last_lower= last_lower+3;
                    confirmed_ticket--;
                    passenger.add(p);
                    Main.confirmed_list.add(p);
                    System.out.println("Your ticket is confirmed.");
                    System.out.println("Ticket number is"+ ticket_count);
                    p.ticket_id=ticket_count;
                    ticket_count= ticket_count+1;
                    break;
                    
                }
                else{
                    System.out.println("L berth is not available... Choose another berth");
                    berthAvailability();
                    break;
                }
                case "M": 
                if( last_middle<=6){
                    middle_berth_count++;
                    p.seat_no = last_middle;
                    last_middle= last_middle+3;
                    confirmed_ticket--;
                    passenger.add(p);
                    Main.confirmed_list.add(p);
                    System.out.println("Your ticket is confirmed.");
                    System.out.println("Ticket number is"+ ticket_count);
                    p.ticket_id=ticket_count;
                    ticket_count= ticket_count+1;
                    break;
                }
                else{
                    System.out.println("M berth is not available... Choose another berth");
                    berthAvailability();
                    break;
                }
            }
            System.out.println(confirmed_ticket);
            
        }
        else{
            if(Main.RAC_Queue.size()<RAC ){
                p.seat_no=0;
                p.ticket_id = ticket_count;
                
                System.out.println("Your ticket is in RAC");
                System.out.println("Ticket number is"+ ticket_count);
                Main.RAC_Queue.add(p);
                RAC--;
                
            }
            else if (Main.Waitinglist_Queue.size()<waiting_list){
                p.seat_no=0;
                p.ticket_id = ticket_count;
                System.out.println("Your ticket is in waiting list");
                System.out.println("Ticket number is"+ ticket_count);
                // p.seat_no=-1;
                Main.Waitinglist_Queue.add(p);
                waiting_list--;

            }
                ticket_count= ticket_count+1;
            
        }
        
    }
    public static void berthAvailability(){
        System.out.println("L berth : "+ (2- lower_berth_count) );
        System.out.println("M berth : "+ (2- middle_berth_count) );
        System.out.println("U berth : "+ (2- upper_berth_count) );
    }
    public static void viewRACTicket(ArrayList<Passenger> p)
    {
        if(Main.RAC_Queue.size()!=0){

            for(Passenger pa :Main.RAC_Queue){
                System.out.println("Passenger name " +pa.name);
                System.out.println("Passenger age " +pa.age);
                System.out.println("Passenger berth " +pa.berth);
                // System.out.println("Passenger seat number " +pa.seat_no);
                System.out.println("Passenger ticket number " +pa.ticket_id);
                System.out.println("-----------------------------------");
                
            }
        }
        else{
            System.out.println("There are no RAC tickets as of now");
        }
        
        
    }
    public static void viewWaitingTicket(ArrayList<Passenger> p)
    {
        if(Main.Waitinglist_Queue.size()!=0){

            for(Passenger pa :Main.Waitinglist_Queue){
                System.out.println("Passenger name " +pa.name);
                System.out.println("Passenger age " +pa.age);
                System.out.println("Passenger berth " +pa.berth);
                // System.out.println("Passenger seat number " +pa.seat_no);
                System.out.println("Passenger ticket number " +pa.ticket_id);
                System.out.println("-----------------------------------");
                
            }
        }
        else{
            System.out.println("There are no waiting list tickets as of now");
        }
        
        
    }
    public static void viewConfirmedTicket(ArrayList<Passenger> p)
    {
        if(Main.confirmed_list.size()!=0){

            for(Passenger pa :Main.confirmed_list){
                System.out.println("Passenger name " +pa.name);
                System.out.println("Passenger age " +pa.age);
                System.out.println("Passenger berth " +pa.berth);
                System.out.println("Passenger seat number " +pa.seat_no);
                System.out.println("Passenger ticket number " +pa.ticket_id);
                System.out.println("-----------------------------------");
                
            }
        }
        else{
            System.out.println("There are no waiting list tickets as of now");
        }
        
        
    }
    public static void cancelTicket(int t_id, ArrayList<Passenger> p)
    {
        int s=0;
        String bb=null;
        Passenger pp = p.get(0);
        
        for(int i=0;i<p.size();i++){
            pp=p.get(i);
            if(pp.ticket_id==t_id){
                
                s= pp.seat_no;
                bb= pp.berth;
                p.remove(pp);
                Main.confirmed_list.remove(pp);

                
            }
        }
        if (Main.RAC_Queue.peek()!= null){
            pp.seat_no=s;
            pp.berth=bb;
            p.add(Main.RAC_Queue.poll());
            RAC++;

        }
        if (Main.Waitinglist_Queue.peek()!= null){
            pp.seat_no=0;
            pp.berth=null;
            Main.RAC_Queue.add(Main.Waitinglist_Queue.poll());
            
            waiting_list++;

        }
        // for(int i=0;i<p.size();i++){
        //     pp=p.get(i);
        //     if(pp.seat_no==0){
                
        //         pp.seat_no=s;
        //         p.set(i, pp);
        //         RAC++;
                
        //     }
        // }
       
         
        // for(Passenger pa : p){
        //     if(pa.seat_no==-1){
        //         pa.seat_no=0;
        //         break;
        //     }
        // }
        System.out.println("Ticket number : "+((pp.ticket_id)==1?pp.ticket_id:(pp.ticket_id)-1) + " has been cancelled!!!");

        

    }
    // public static  void viewRACTicket(ArrayList<Passenger> p){
        
    //     for(Passenger pa :p){
    //         if(pa.seat_no==0){
    //             System.out.println("Passenger name" +pa.name);
    //             System.out.println("Passenger age" +pa.age);
    //             // System.out.println("Passenger berth" +pa.berth);
    //             // System.out.println("Passenger seat number" +pa.seat_no);
    //             System.out.println("Passenger ticket number" +pa.ticket_id);
    //             System.out.println("-----------------------------------");
    //         }
    //     }
    // }
    // public static void viewWaitingTicket(ArrayList<Passenger> p){
    //     for(Passenger pa :p){
    //         if(pa.seat_no==-1){
    //             System.out.println("Passenger name" +pa.name);
    //             System.out.println("Passenger age" +pa.age);
    //             // System.out.println("Passenger berth" +pa.berth);
    //             // System.out.println("Passenger seat number" +pa.seat_no);
    //             System.out.println("Passenger ticket number" +pa.ticket_id);
    //             System.out.println("-----------------------------------");
    //         }
    //     }

    // }
}
