#version 330
layout(location=0) in vec3 eckenAusJava; //gibt coord weiter
layout(location=1) in vec3 farbenAusJava;

uniform mat4 transformationMatrix2;
uniform mat4 projectionMatrix;


out vec3 vcolor;



void main() {
    //color
    vcolor = farbenAusJava;
    //letzte Koordinate 1 dann punkt (immer in glsl)
    gl_Position = transformationMatrix*projectionMatrix*vec4(eckenAusJava, 1.0f);
}