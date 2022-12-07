class GaltoLit {
    public static void main(String [] args) {
        double litres;
        double gallons;
        gallons = 10;
        if (args.length != 0){
            System.out.println("unnecessary input arguments: " + args[0]);
            // gallons = ;
        }
        litres = gallons * 3.7854;
        
        System.out.println(gallons + " Gallons is " + litres + " in Litres.");
    }
}