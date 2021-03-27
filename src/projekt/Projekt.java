package projekt;

import static org.lwjgl.opengl.GL30.*;


import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;
import lenz.opengl.Texture;

public class Projekt extends AbstractOpenGLBase {

	Matrix4 mediumMatrix = new Matrix4();
	Matrix4 bigMatrix = new Matrix4();
	Matrix4 smallMatrix = new Matrix4();
	Matrix4 cubeMatrix = new Matrix4();
	Matrix4 gemMatrix = new Matrix4();
	Matrix4 projectionMatrix;
	private ShaderProgram shaderProgram;
	private float angle;
	private float[] pyramid;
	private float[] cube;
	private float[] gem;
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

	private int textureIDPyramid;
	private int textureIDPyramid2;
	private int vaoId1;
	private int vaoId2;
	private int vaoId3;

	private float[] cubeVertices = Coordinates.getCubeVertices();
	private float[] tVertices = Coordinates.getSphereVertices();

	public static void main(String[] args) {
		new Projekt().start("CG Projekt", 700, 700);
	}

	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("projekt");
		glUseProgram(shaderProgram.getId());

		createArrays();
		initializePyramid();
		initializeGem();
//		makeTetra();
		initializeCube();

		 //Camera
		// Matrix an Shader übertragen (muss nur einmal übertragen werden nicht die ganze Zeit)


		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
		//glEnable(GL_CULL_FACE); // backface culling aktivieren
		projectionMatrix  = new Matrix4();
		int camloc = glGetUniformLocation(shaderProgram.getId(), "projectionMatrix");
		glUniformMatrix4fv(camloc, false, projectionMatrix.getValuesAsArray());

	}

	protected void makeTetra() {

		glUseProgram(shaderProgram.getId());
		vaoId1 = glGenVertexArrays();
		glBindVertexArray(vaoId1);

		attachVBO(tVertices, 0, 3);
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

	private void initializeCube() {
		glUseProgram(shaderProgram.getId());

		//Vertices
		vaoId2 = glGenVertexArrays();
		glBindVertexArray(vaoId2);

		attachVBO(cube,0,3);
		//Colors zum VAO hinzufügen
		attachVBO( farben, 1, 3);
	}

	private void initializeGem() {
		glUseProgram(shaderProgram.getId());

		//Vertices
		vaoId3 = glGenVertexArrays();
		glBindVertexArray(vaoId3);

		attachVBO(gem,0,3);
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

		glUseProgram(shaderProgram.getId());

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
		cube = new float[]{

				// Vorne L.Drei
				-0.5f,  0.5f, -0.5f,  //C
				-0.5f, -0.5f, -0.5f,  //A
				0.5f, -0.5f, -0.5f,  //B

				// Vorne R.Drei
				0.5f, -0.5f, -0.5f,  //B
				0.5f,  0.5f, -0.5f,  //D
				-0.5f,  0.5f, -0.5f,  //C

				// Rechts L.Drei
				0.5f,  0.5f, -0.5f,  //D
				0.5f, -0.5f, -0.5f,  //B
				0.5f, -0.5f,  0.5f,  //E

				// Rechts R.Drei
				0.5f, -0.5f,  0.5f,  //E
				0.5f,  0.5f,  0.5f,  //G
				0.5f,  0.5f, -0.5f,  //D

				// Hinten L.Drei
				0.5f,  0.5f,  0.5f,  //G
				0.5f, -0.5f,  0.5f,  //E
				-0.5f, -0.5f,  0.5f,  //F

				// Hinten R.Drei
				-0.5f, -0.5f,  0.5f,  //F
				-0.5f,  0.5f,  0.5f,  //H
				0.5f,  0.5f,  0.5f,  //G

				// Links L.Drei
				-0.5f,  0.5f,  0.5f,  //H
				-0.5f, -0.5f,  0.5f,  //F
				-0.5f, -0.5f, -0.5f,  //A

				// Links R.Drei
				-0.5f, -0.5f, -0.5f,  //A
				-0.5f,  0.5f, -0.5f,  //C
				-0.5f,  0.5f,  0.5f,  //H

				// Boden L.Drei
				0.5f, -0.5f, -0.5f,  //B
				-0.5f, -0.5f, -0.5f,  //A
				-0.5f, -0.5f,  0.5f,  //F

				// Boden R.Drei
				-0.5f, -0.5f,  0.5f,  //F
				0.5f, -0.5f,  0.5f,  //E
				0.5f, -0.5f, -0.5f,  //B

				// Dach L.Drei
				-0.5f,  0.5f,  0.5f,  //H
				-0.5f,  0.5f, -0.5f,  //C
				0.5f,  0.5f, -0.5f,  //D

				// Dach R.Drei
				0.5f,  0.5f, -0.5f,  //D
				0.5f,  0.5f,  0.5f,  //G
				-0.5f,  0.5f,  0.5f   //H

		};
		gem = new float[] {
				// 1s triangle
				0.0f, 0.4f, 0.0f,
				-0.2f, 0.0f, -0.2f,
				0.2f, 0.0f, -0.2f,
				// 2nd triangle
				0.0f, -0.4f, 0.0f,
				-0.2f, 0.0f, -0.2f,
				0.2f, 0.0f, -0.2f,
				// 3rd triangle
				0.0f, 0.4f, 0.0f,
				0.2f, 0.0f, -0.2f,
				0.2f, 0.0f, 0.2f,
				// 4th triangle
				0.0f, -0.4f, 0.0f,
				0.2f, 0.0f, -0.2f,
				0.2f, 0.0f, 0.2f,
				// 5th triangle
				0.0f, 0.4f, 0.0f,
				0.2f, 0.0f, 0.2f,
				-0.2f, 0.0f, 0.2f,
				// 6th triangle
				0.0f, -0.4f, 0.0f,
				0.2f, 0.0f, 0.2f,
				-0.2f, 0.0f, 0.2f,
				// 7th triangle
				0.0f, 0.4f, 0.0f,
				-0.2f, 0.0f, 0.2f,
				-0.2f, 0.0f, -0.2f,
				// 8th triangle
				0.0f, -0.4f, 0.0f,
				-0.2f, 0.0f, 0.2f,
				-0.2f, 0.0f, -0.2f,
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
		angle += 1f;

		//small
		smallMatrix = new Matrix4();
		smallMatrix.translate(-0.5f, -1f , 0f);
		smallMatrix.rotateY(angle);
		smallMatrix.scale(0.5f);

		//medium
		mediumMatrix = new Matrix4();
		mediumMatrix.rotateY(angle).rotateZ(180f);
		mediumMatrix.translate(-0.5f, 0.5f, 0f);

		//large
		bigMatrix = new Matrix4();
		bigMatrix.translate(-0.3f, 0f, 0f);
		bigMatrix.rotateY(angle);
		bigMatrix.scale(1.5f);

		//cube
		cubeMatrix = new Matrix4();
		cubeMatrix.rotateZ(angle).rotateY(angle);
		cubeMatrix.scale(0.1f);
		cubeMatrix.translate(0.5f, 0.5f, 0f);

		//gem
		gemMatrix = new Matrix4();
		gemMatrix.rotateY(angle);

	}

	@Override
	protected void render() {
		glClearColor(0.2f, 0.3f, 0.3f, 1.0f); //changes background color
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		// Matrix an Shader übertragen

		int loc = glGetUniformLocation(shaderProgram.getId(), "transformationMatrix");
		glBindVertexArray(vaoId1);
		glUniformMatrix4fv(loc, false, smallMatrix.getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, 18);
		//mittel
		glUniformMatrix4fv(loc, false, mediumMatrix.getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, 18);
		//groß
		glUniformMatrix4fv(loc, false, bigMatrix.getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, 18);
		//klein pyramide


		//cube
		int loc2 = glGetUniformLocation(shaderProgram.getId(), "transformationMatrix");
		glUniformMatrix4fv(loc2, false, cubeMatrix.getValuesAsArray());
		glBindVertexArray(vaoId2);
		glDrawArrays(GL_TRIANGLES, 0, 36);

		int loc3 = glGetUniformLocation(shaderProgram.getId(), "transformationMatrix");
		glUniformMatrix4fv(loc3, false, gemMatrix.getValuesAsArray());
		glBindVertexArray(vaoId3);
		glDrawArrays(GL_TRIANGLES, 0, 24);

	}

	@Override
	public void destroy() {
	}
}
