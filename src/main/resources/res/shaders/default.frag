#version 120

uniform sampler2D sTexture;
uniform bool useTexture;

void main() {
	if (useTexture) {
		gl_FragColor = gl_Color * texture2D(sTexture, gl_TexCoord[0].st);
	} else {
		gl_FragColor = gl_Color;
	}
}