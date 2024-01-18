#ifdef GL_ES
precision mediump float;
#endif

#define PROCESSING_TEXTURE_SHADER

uniform sampler2D texture;
varying vec4 vertTexCoord;
uniform vec2 resolution;

uniform float offsetX;
uniform float offsetY;
uniform float scaleFactor;

void main() {
  vec2 coord = gl_FragCoord.xy;
  vec2 uv = vec2((coord.x - offsetX) / 1200., (coord.y + offsetY) / 800.);
  uv.y = 1.0 - uv.y; // Flip the y-coordinate

  // Calculate the scaled UV coordinates
  vec2 scaledUV = floor(uv * vec2(1.0 / scaleFactor) * resolution.xy) / resolution.xy + 0.5/resolution.xy;

  // Retrieve the color from the texture at the scaled UV coordinates
  vec4 pixelColor = texture2D(texture, vec2(scaledUV.x, 1. - scaledUV.y));

  // Output the color
  gl_FragColor = pixelColor;
}