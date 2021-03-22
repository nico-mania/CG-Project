package ab3;

//Alle Operationen ändern das Matrixobjekt selbst und geben das eigene Matrixobjekt zurück
//Dadurch kann man Aufrufe verketten, z.B.
//Matrix4 m = new Matrix4().scale(5).translate(0,1,0).rotateX(0.5f);
public class Matrix4 {
	float [][] mat4;

	public Matrix4() {
		// TODO mit der Identitätsmatrix initialisieren
		mat4 = new float[][] {
				{1, 0, 0, 0}, 	//Spalte1
				{0, 1, 0, 0}, 	//Spalte2
				{0, 0, 1, 0}, 	//Spalte3
				{0, 0, 0, 1} 	//Spalte4
		};
		
	}

	public Matrix4(Matrix4 copy) {
		// TODO neues Objekt mit den Werten von "copy" initialisieren
		mat4 = new float[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				mat4[i][j] = copy.mat4[i][j];
			}
		}
	}

	public Matrix4(float near, float far) {
		// TODO erzeugt Projektionsmatrix mit Abstand zur nahen Ebene "near" und Abstand zur fernen Ebene "far", ggf. weitere Parameter hinzufügen
		mat4 = new float[4][4];
		mat4[0][0] = near;
		mat4[1][1] = near;
		mat4[2][2] = (-near - far ) / (far - near);
		mat4[2][3] = -1;
		mat4[3][2] = (-2 * far * near) /  (far - near );
		mat4[3][3] = 0;
	}

	public Matrix4 multiply(Matrix4 other) {
		// TODO hier Matrizenmultiplikation "this = other * this" einfügen
		float [][] erg = new float[4][4];
		//Spalten
		for(int i = 0; i < 4; i++) {
			//Zeilen
			for(int j = 0; j < 4; j++) {
				//Zeile*Spalte
				for(int k = 0; k < 4; k++) {
					erg[i][j] += other.mat4[k][j] * mat4[i][k];
				}
			}
		}
		this.mat4 = erg;
		return this;
	}

	public Matrix4 translate(float x, float y, float z) {
		// TODO Verschiebung um x,y,z zu this hinzufügen
		float[][] trans = new float[][] {
				{1, 0, 0, x}, 	//Spalte1
				{0, 1, 0, y}, 	//Spalte2
				{0, 0, 1, z}, 	//Spalte3
				{0, 0, 0, 1} 	//Spalte4
		};
		//Erstelle neues Matrixobjekt für multiply methode
		Matrix4 transmat = new Matrix4();
		transmat.mat4 = trans;

		return multiply(transmat);
	}

	public Matrix4 scale(float uniformFactor) {
		// TODO gleichmäßige Skalierung um Faktor "uniformFactor" zu this hinzuf�gen
		float[][] scale = new float[][] {
				{uniformFactor, 0, 0, 0}, 	//Spalte1
				{0, uniformFactor, 0, 0}, 	//Spalte2
				{0, 0, uniformFactor, 0}, 	//Spalte3
				{0, 0, 0, 1} 	//Spalte4
		};
		//Erstelle neues Matrixobjekt für multiply methode
		Matrix4 scalmat = new Matrix4();
		scalmat.mat4 = scale;

		return multiply(scalmat);
	}

	public Matrix4 scale(float sx, float sy, float sz) {
		// TODO ungleichförmige Skalierung zu this hinzuf�gen
		float[][] scale = new float[][] {
				{sx, 0, 0, 0}, 	//Spalte1
				{0, sy, 0, 0}, 	//Spalte2
				{0, 0, sz, 0}, 	//Spalte3
				{0, 0, 0, 1} 	//Spalte4
		};
		//Erstelle neues Matrixobjekt für multiply methode
		Matrix4 scalmat = new Matrix4();
		scalmat.mat4 = scale;

		return multiply(scalmat);
	}

	public Matrix4 rotateX(float angle) {
		// TODO Rotation um X-Achse zu this hinzuf�gen
		double rad = Math.toRadians(angle);
		//Rotationsmatrix
		Matrix4 rotmat = new Matrix4();
		rotmat.mat4[0][0] = 1;
		rotmat.mat4[1][1] = (float) Math.cos(rad);
		rotmat.mat4[1][2] = (float) Math.sin(rad);
		rotmat.mat4[2][1] = (float)-Math.sin(rad);
		rotmat.mat4[2][2] = (float) Math.cos(rad);
		rotmat.mat4[3][3] = 1;
		return multiply(rotmat);
	}

	public Matrix4 rotateY(float angle) {
		// TODO Rotation um Y-Achse zu this hinzuf�gen
		double rad = Math.toRadians(angle);
		//Rotationsmatrix
		Matrix4 rotmat = new Matrix4();
		rotmat.mat4[0][0] = (float) Math.cos(rad);
		rotmat.mat4[0][2] = (float) Math.sin(rad);
		rotmat.mat4[1][1] = 1;
		rotmat.mat4[2][0] = (float)-Math.sin(rad);
		rotmat.mat4[2][2] = (float) Math.cos(rad);
		rotmat.mat4[3][3] = 1;
		return multiply(rotmat);
	}

	public Matrix4 rotateZ(float angle) {
		// TODO Rotation um Z-Achse zu this hinzuf�gen
		double rad = Math.toRadians(angle);
		//Rotationsmatrix
		Matrix4 rotmat = new Matrix4();
		rotmat.mat4[0][0] = (float) Math.cos(rad);
		rotmat.mat4[0][1] = -(float) Math.sin(rad);
		rotmat.mat4[1][0] = (float) Math.sin(rad);
		rotmat.mat4[1][1] = (float) Math.cos(rad);
		rotmat.mat4[2][2] = 1;
		rotmat.mat4[3][3] = 1;
		return multiply(rotmat);
	}

	public float[] getValuesAsArray() {
		// TODO hier Werte in einem Float-Array mit 16 Elementen (spaltenweise gefüllt) herausgeben
		float[] values = new float [16];
		for(int i = 0; i < mat4.length; i++) {
			for(int j = 0; j < mat4.length; j++) {
				values[i*4+j] = mat4[i][j];
			}
		}

		return values;
	}
}
