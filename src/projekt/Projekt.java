package projekt;

import static org.lwjgl.opengl.GL30.*;

import ab3.Matrix4;
import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Projekt extends AbstractOpenGLBase {

	private Matrix4 drehdings = new Matrix4();
	private ShaderProgram shaderProgram;

	public static void main(String[] args) {
		new Projekt().start("CG Projekt", 700, 700);
	}

	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("projekt");
		glUseProgram(shaderProgram.getId());

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen

		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
		//glEnable(GL_CULL_FACE); // backface culling aktivieren

		float [] dreiecksKoordinaten = new float [] {
				-0.5f, -0.5f,
				0.0f, 0.5f,
				0.5f, -0.5f
		};

		float [] farben = new float [] {
				1.0f, 0.0f, 0.0f, //r
				0.0f, 1.0f, 0.0f, //g
				0.0f, 0.0f, 1.0f  //b
		};

		//Vertices
		int vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);
		int vboId = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER,dreiecksKoordinaten, GL_STATIC_DRAW);
		//2 da 2Koordinaten
		glVertexAttribPointer(0, 2, GL_FLOAT,false, 0, 0);
		glEnableVertexAttribArray(0);

		//Colors
		attachVBO(vboId, farben, 1, 3);
	}

	//Methode um VBO zum VAO hinzuzufügen
	private void attachVBO (int vboId, float[] array, int num, int vectype) {
		vboId = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, array, GL_STATIC_DRAW);
		glVertexAttribPointer(num, vectype, GL_FLOAT,false, 0, 0);
		glEnableVertexAttribArray(num);
	}

	@Override
	public void update() {
		// Transformation durchführen (Matrix anpassen)
		//drehdings = new Matrix4();
		drehdings.rotateY(1.0f);
		//drehdings.translate(0f, 0f, -2f);
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		// Matrix an Shader übertragen
		int loc = glGetUniformLocation(shaderProgram.getId(), "drehdings");
		glUniformMatrix4fv(loc, false, drehdings.getValuesAsArray());
		// VAOs zeichnen
		glDrawArrays(GL_TRIANGLES, 0, 3);
	}

	@Override
	public void destroy() {
	}
}
