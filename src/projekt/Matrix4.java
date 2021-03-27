package projekt;

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
		this.mat4 = copy.mat4.clone();
	}

	public Matrix4(float near, float far) {
		// TODO erzeugt Projektionsmatrix mit Abstand zur nahen Ebene "near" und Abstand zur fernen Ebene "far", ggf. weitere Parameter hinzufügen
		mat4 = new float[4][4];
		mat4[0][0] = near;
		mat4[1][1] = near;
		mat4[2][2] = (-far -near  ) / (far - near);
		mat4[2][3] = -1;
		mat4[3][2] = (-2 * far * near) /  (far - near );
		mat4[3][3] = 0;
	}

	public Matrix4 multiply(Matrix4 other) {
		// TODO hier Matrizenmultiplikation "this = other * this" einfügen
		float[][] m = new float[4][4];
		for(int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				for (int i = 0; i < 4; i++) {
					m[row][col] += other.mat4[row][i] * this.mat4[i][col];
				}
			}
		}
		this.mat4 = m;
		return this;
	}

	public Matrix4 translate(float x, float y, float z) {
		// TODO Verschiebung um x,y,z zu this hinzufügen
		//Erstelle Translationsmatrix
		Matrix4 transmat = new Matrix4();
		transmat.mat4 = new float[][] {
				{1, 0, 0, x}, 	//Spalte1
				{0, 1, 0, y}, 	//Spalte2
				{0, 0, 1, z}, 	//Spalte3
				{0, 0, 0, 1} 	//Spalte4
		};
		//Erstelle neues Matrixobjekt für multiply methode

		this.multiply(transmat);
		return this;
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

		this.multiply(scalmat);
		return this;
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

		this.multiply(scalmat);
		return this;
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

		this.multiply(rotmat);
		return this;
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

		this.multiply(rotmat);
		return this;
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

		this.multiply(rotmat);
		return this;
	}

	public float[] getValuesAsArray() {
		// TODO hier Werte in einem Float-Array mit 16 Elementen (spaltenweise gefüllt) herausgeben
		float[] values = new float [16];
		for(int col = 0; col < 4; col++) {
			for(int row = 0; row < 4; row++) {
				values[col * 4 + row] = this.mat4[row][col];
			}
		}

		return values;
	}
}
