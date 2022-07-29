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
}
