#version 120

uniform sampler2D sTexture;

void main() {
	gl_FragColor = gl_Color * texture2D(sTexture, gl_TexCoord[0].st);
//	gl_FragColor = gl_Color;;
}