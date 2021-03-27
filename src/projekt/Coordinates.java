package projekt;

public class Coordinates {


        private static float[] sphereVertices = new float[] {
                0.1f, -0.1f, -0.1f, 	// B
                -0.1f, -0.1f, -0.1f, 	// A
                0f, 0.1f, 0f, 			// top

                -0.1f, -0.1f, -0.1f, 	// A
                -0.1f, -0.1f, 0.1f, 	// D
                0f, 0.1f, 0f, 			// top

                -0.1f, -0.1f, 0.1f, 	// D
                0.1f, -0.1f, 0.1f, 		// C
                0f, 0.1f, 0f, 			// top

                0.1f, -0.1f, 0.1f, 		// C
                0.1f, -0.1f, -0.1f, 	// B
                0f, 0.1f, 0f, 			// top
                //floor
                -0.1f, -0.1f, -0.1f, 	// A
                0.1f, -0.1f, 0.1f, 		// C
                -0.1f, -0.1f, 0.1f, 	// D

                0.1f, -0.1f, -0.1f, 	// B
                0.1f, -0.1f, 0.1f, 		// C
                -0.1f, -0.1f, -0.1f		// A
        };

        //cube
        private static float[] cubeVertices = new float[]{
                //vorne
                -0.5F, -0.5F, 0.5F, //A
                0.5F, -0.5F, 0.5F,  //B
                -0.5F, 0.5F, 0.5F,  //D

                -0.5F, 0.5F, 0.5F,  //D
                0.5F, -0.5F, 0.5F,  //B
                0.5F, 0.5F, 0.5F,   //C

                //hinten
                -0.5F, -0.5F, -0.5F,
                -0.5F, 0.5F, -0.5F,
                0.5F, -0.5F, -0.5F,

                -0.5F, 0.5F, -0.5F,
                0.5F, 0.5F, -0.5F,
                0.5F, -0.5F, -0.5F,

                //unten
                -0.5F, -0.5F, 0.5F,
                0.5F, -0.5F, -0.5F,
                0.5F, -0.5F, 0.5F,

                -0.5F, -0.5F, 0.5F,
                -0.5F, -0.5F, -0.5F,
                0.5F, -0.5F, -0.5F,

                //oben
                -0.5F, 0.5F, 0.5F,
                0.5F, 0.5F, 0.5F,
                0.5F, 0.5F, -0.5F,

                -0.5F, 0.5F, 0.5F,
                0.5F, 0.5F, -0.5F,
                -0.5F, 0.5F, -0.5F,

                //links
                -0.5F, 0.5F, 0.5F,
                -0.5F, 0.5F, -0.5F,
                -0.5F, -0.5F, 0.5F,

                -0.5F, -0.5F, 0.5F,
                -0.5F, 0.5F, -0.5F,
                -0.5F, -0.5F, -0.5F,

                //rechts
                0.5F, 0.5F, 0.5F,
                0.5F, -0.5F, 0.5F,
                0.5F, 0.5F, -0.5F,

                0.5F, -0.5F, 0.5F,
                0.5F, -0.5F, -0.5F,
                0.5F, 0.5F, -0.5F
        };

        static float[] getCubeVertices() {
            return cubeVertices;
        }

        static float[] getSphereVertices(){
            return sphereVertices;
        }

}
