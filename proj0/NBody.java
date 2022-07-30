public class NBody {

    public static double readRadius(String file){
        In in = new In(file);
        int n = in.readInt();
        double rad = in.readDouble();
        return rad;
    }
    public static Body[] readBodies(String file){
        In in = new In(file);
        int n = in.readInt();
        Body[] planets = new Body[n];
        double rad = in.readDouble();
        for (int i = 0; i < n; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
            double xxVel = in.readDouble();
			double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFile = in.readString();
            planets[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFile);
			}
        return planets;			
		}
        public static void main(String[] args){
            double T;
            double dt;
            StdAudio.loop("./audio/2001.wav");
            try{
                T = Double.parseDouble(args[0]);
            }
            catch (NumberFormatException e){
                T = 0.0;
                System.out.println("Error converting double to str - T set to 0.0");
            }
            try{
                dt = Double.parseDouble(args[1]);
            }
            catch (NumberFormatException e){
                dt = 0.0;
                System.out.println("Error converting double to str - dt set to 0.0");
            }
            String file = args[2];
            Body[] allPlanets = readBodies(file);
            double radius = readRadius(file);
            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0,0, "./images/starfield.jpg");
            for (Body b : allPlanets){
                b.draw();
            }
            StdDraw.enableDoubleBuffering();
            int nPlanets = allPlanets.length;
            // T is a double
            int end_time = (int)T;
            // loop from time = 0 to T (included)
            for (int start_time = 0; start_time <= end_time; start_time++){
                
                double xForces[] = new double[(int)nPlanets];
                double yForces[] = new double[(int)nPlanets];;

                for (int i = 0; i < nPlanets; i++){
                    xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                    yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
                    allPlanets[i].update(dt, xForces[i], yForces[i]);
                }
                StdDraw.picture(0,0, "./images/starfield.jpg");
                for (Body b : allPlanets){
                    b.draw();
                }
                StdDraw.show();
                StdDraw.pause(10);
                start_time+=(int)dt;
            }
            StdOut.printf("%d\n", nPlanets);
            StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < nPlanets; i++){
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                  allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName); 
            }
        }
}
