#ifdef GL_ES
precision mediump float;
#endif

uniform vec2 u_resolution;
uniform float u_time;
uniform float u_scale;
uniform vec3 u_texture_color;
uniform vec2 u_offset;

void main() {
    // float u_scale = 1.;
    // vec3 u_texture_color = vec3(0.015,0.072,0.535);
    
    vec2 uv = (gl_FragCoord.xy - u_offset) / u_resolution.xy;
    uv.x /= 1.5;
    uv /= u_scale;
   
    //background texture
   	//vec4 texture_color = texture(iChannel0, uv);
    
   	//background color rgb( 49/255, 169/255, 238/255, 255/255 ) -- 0.192156862745098, 0.6627450980392157, 0.9333333333333333
    // vec4 texture_color = vec4(0.192156862745098, 0.6627450980392157, 0.9333333333333333, 1.0);
    vec4 texture_color = vec4(u_texture_color,1.);
    
    vec4 k = vec4(u_time)*0.8;
	k.xy = uv * 7.0;
    float val1 = length(0.5-fract(k.xyw*=mat3(vec3(-2.0,-1.0,0.0), vec3(3.0,-1.0,1.0), vec3(1.0,-1.0,-1.0))*0.5));
    float val2 = length(0.5-fract(k.xyw*=mat3(vec3(-2.0,-1.0,0.0), vec3(3.0,-1.0,1.0), vec3(1.0,-1.0,-1.0))*0.2));
    float val3 = length(0.5-fract(k.xyw*=mat3(vec3(-2.0,-1.0,0.0), vec3(3.0,-1.0,1.0), vec3(1.0,-1.0,-1.0))*0.5));
    vec4 color = vec4 ( pow(min(min(val1,val2),val3), 10.) * 400.)+texture_color;
    
    // color = floor(color*5.)/5.;
    
    gl_FragColor = color;
}