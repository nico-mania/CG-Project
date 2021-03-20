#version 330

vec2 u_resolution;
out vec3 pixelFarbe;

void main(){

/*    vec2 pixelXY = gl_FragCoord.xy; // Aufgabe 1

    pixelFarbe = vec3(1.0, 0.0, 0.0);

    if(pixelXY.x < 10 || pixelXY.x > 690 || pixelXY.y<10 ||pixelXY.y > 690){
        pixelFarbe = vec3(0.0, 0.0, 1.0);
    }*/
/*    vec2 pixelXY = gl_FragCoord.xy;   //Aufgabe 2

    pixelFarbe = vec3(1.0, 0.0, 0.0);

    vec2 center = vec2(350, 350);
    float d = distance(center, gl_FragCoord.xy);
    if(d>100){
        pixelFarbe = vec3(1.0, 1.0, 1.0);
    }*/
    vec2 pixelXY = gl_FragCoord.xy;   //Aufgabe 3

    pixelFarbe = vec3(1.0, 1.0, 0.0);

    vec2 auge1 = vec2(150, 550);
    float d = distance(auge1, gl_FragCoord.xy);
    if(d<100){
        pixelFarbe = vec3(0.0, 0.0, 0.0);
    }
    vec2 auge2 = vec2(550, 550);
    float d2 = distance(auge2, gl_FragCoord.xy);
    if(d2<100){
        pixelFarbe = vec3(0.0, 0.0, 0.0);
    }
    vec2 mund = vec2(350, 200);
    float d3 = distance(mund, gl_FragCoord.xy);
    if(d3<150){
        pixelFarbe = vec3(0.0, 0.0, 0.0);
    }
    if(pixelXY.x > 0 && pixelXY.y > 610){
        pixelFarbe = vec3(1.0, 1.0, 0.0);
    }
    if(pixelXY.x < 700 && pixelXY.y < 200){
        pixelFarbe = vec3(1.0, 1.0, 0.0);
    }
}