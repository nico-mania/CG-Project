#version 330
layout(location=0) in vec2 eckenAusJava;
layout(location=1) in vec3 farbenAusJava;

uniform mat4 drehdings;

float rotationValue = 0.3;
out vec3 vcolor;


vec2 rot (vec2 v, float w) {
    mat2 rm = mat2 (cos(w), sin(w), -sin(w), cos(w));
    return rm*v;
}

void main() {
    //color
    vcolor = farbenAusJava;
    //letzte Koordinate 1 dann punkt (immer in glsl)
    gl_Position = drehdings*vec4(rot(eckenAusJava, rotationValue), 0.0, 1.0);
}