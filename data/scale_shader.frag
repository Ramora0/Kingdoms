#ifdef GL_ES
precision mediump float;
#endif

#define PROCESSING_TEXTURE_SHADER

uniform sampler2D texture;
varying vec4 vertTexCoord;
uniform vec2 resolution;
uniform float scaleFactor;

void main() {
  vec2 uv = gl_FragCoord.xy / resolution.xy;

  // Calculate the scaled UV coordinates
  vec2 scaledUV = floor(uv * vec2(1.0 / scaleFactor) * resolution.xy) / resolution.xy + 0.5/resolution.xy;

  // Retrieve the color from the texture at the scaled UV coordinates
  vec4 pixelColor = texture2D(texture, scaledUV);

  // Output the color
  gl_FragColor = pixelColor;
}