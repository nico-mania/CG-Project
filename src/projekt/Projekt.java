package projekt;

import static org.lwjgl.opengl.GL30.*;

import ab3.Matrix4;
import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;
import lenz.opengl.Texture;

public class Projekt extends AbstractOpenGLBase {

	private Matrix4 transformationMatrix = new Matrix4();
	private Matrix4 projectionMatrix2 = new Matrix4(1, 100);
	private ShaderProgram shaderProgram;
	private float angle;
	private float[] pyramid;
	private float[] triangle;
	private float[] uvkoordiaten;
	private float[] uvkoordinatenCube;
	private float[] farben;
	private float[] normalen;
	private float[] normalenCube;
	private Texture tex0;
	private Texture tex1;
	private Texture tex2;
	private int textureIdCube;
	private int vaoId2;
	private int textureIDPyramid;
	private int textureIDPyramid2;
	private int vaoId1;
	private Matrix4 rotmat = new Matrix4();
	private Matrix4 rotmat2 = new Matrix4();
	private Matrix4 persmat = new Matrix4();

	public static void main(String[] args) {
		new Projekt().start("CG Projekt", 700, 700);
	}

	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("projekt");
		glUseProgram(shaderProgram.getId());

		createArrays();
		initializePyramid();

		rotmat = new Matrix4();
		rotmat2 = new Matrix4();
		persmat = new Matrix4(1f, 100f);
		// Matrix an Shader �bertragen (muss nur einmal übertragen werden nicht die ganze Zeit)
		int persloc = glGetUniformLocation(shaderProgram.getId(), "persmat");
		glUniformMatrix4fv(persloc, false, persmat.getValuesAsArray());

		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
//		glEnable(GL_CULL_FACE); // backface culling aktivieren

	}

	private void initializePyramid() {
		glUseProgram(shaderProgram.getId());

		//Vertices
		vaoId1 = glGenVertexArrays();
		glBindVertexArray(vaoId1);

		attachVBO(pyramid,0,3);
		//Colors zum VAO hinzufügen
		attachVBO( farben, 1, 3);
	}

	//Methode um VBO zum VAO hinzuzufügen
	private void attachVBO (float[] array, int num, int vectype) {
		int vboId = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, array, GL_STATIC_DRAW);
		glVertexAttribPointer(num, vectype, GL_FLOAT,false, 0, 0);
		glEnableVertexAttribArray(num);
	}

	private void createArrays() {
		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen

		pyramid = new float[]{
				//sides
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
		farben = new float[]{
				0.9f, 0.3f, 0f,	//color first vertex
				0f, 0.9f, 0.3f, //color second vertex
				0f, 0.3f, 0.9f, //color third vertex

				0f, 0.9f, 0.3f, //color first vertex
				0f, 0.9f, 0.3f, //color second vertex
				0f, 0.3f, 0.9f, //color third vertex

				0f, 0.9f, 0.3f, //color first vertex
				0.9f, 0.3f, 0f, //color second vertex
				0f, 0.3f, 0.9f, //color third vertex

				0.9f, 0.3f, 0f, //color first vertex
				0.9f, 0.3f, 0f, //color second vertex
				0f, 0.3f, 0.9f, //color third vertex

				0f, 0.9f, 0.3f, //color first vertex
				0f, 0.9f, 0.3f, //color second vertex
				0f, 0.3f, 0.9f, //color third vertex

				0.9f, 0.3f, 0f, //color first vertex
				0f, 0.9f, 0.3f, //color second vertex
				0f, 0.9f, 0.3f  //color third vertex
		};
	}

	@Override
	public void update() {
		angle += 1.0f;
		transformationMatrix = new Matrix4();
		transformationMatrix.rotateY(angle).rotateX(angle);
	}

	@Override
	protected void render() {
		glClearColor(0.2f, 0.3f, 0.3f, 1.0f); //changes background color
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		// Matrix an Shader übertragen
		int loc = glGetUniformLocation(shaderProgram.getId(), "transformationMatrix");
		glUniformMatrix4fv(loc, false, transformationMatrix.getValuesAsArray());
		int loc2 = glGetUniformLocation(shaderProgram.getId(), "projectionMatrix");
		glUniformMatrix4fv(loc2, false, transformationMatrix.getValuesAsArray());
		// VAOs zeichnen
		glDrawArrays(GL_TRIANGLES, 0, 18);
	}

	@Override
	public void destroy() {
	}
}
