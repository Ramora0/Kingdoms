#ifdef GL_ES
precision mediump float;
#endif

uniform vec2 u_resolution;
uniform vec2 u_mouse;
uniform float u_time;
uniform float u_scale;
uniform float u_speed;
uniform float u_bands;
uniform vec2 u_offset;

vec3 random3(vec3 st){
    st = vec3( dot(st,vec3(127.1,311.7, 74.7)),
               dot(st,vec3(269.5,183.3,246.1)),
               dot(st,vec3(113.5,271.9,124.6)) );
    return -1.0 + 2.0*fract(sin(st)*43758.5453123);
}

float noise(vec3 st) {
    vec3 i = floor(st);
    vec3 f = fract(st);

    vec3 u = f*f*(3.0-2.0*f);

    return mix(mix(mix(dot(random3(i + vec3(0.0,0.0,0.0)), f - vec3(0.0,0.0,0.0)),
                       dot(random3(i + vec3(1.0,0.0,0.0)), f - vec3(1.0,0.0,0.0)), u.x),
                   mix(dot(random3(i + vec3(0.0,1.0,0.0)), f - vec3(0.0,1.0,0.0)),
                       dot(random3(i + vec3(1.0,1.0,0.0)), f - vec3(1.0,1.0,0.0)), u.x), u.y),
               mix(mix(dot(random3(i + vec3(0.0,0.0,1.0)), f - vec3(0.0,0.0,1.0)),
                       dot(random3(i + vec3(1.0,0.0,1.0)), f - vec3(1.0,0.0,1.0)), u.x),
                   mix(dot(random3(i + vec3(0.0,1.0,1.0)), f - vec3(0.0,1.0,1.0)),
                       dot(random3(i + vec3(1.0,1.0,1.0)), f - vec3(1.0,1.0,1.0)), u.x), u.y), u.z);
}

void main() {
    // float u_bands = 5.;
    // float u_speed = .2;
    // float u_scale = 1.;
    
    vec2 st = gl_FragCoord.xy / u_resolution.xy;
    // st.x *= u_resolution.x / u_resolution.y;
    st += u_offset;

    vec3 color = vec3(0.0);

    vec3 pos = vec3(st * u_scale, u_time * u_speed);

    // Create a cartoon-like ocean effect by adjusting the noise function's output
    float n = noise(pos)/2.+0.784;
    
    n = floor(n*u_bands)/u_bands;
    
    color = n*vec3(0.,0.5,1.);

    gl_FragColor = vec4(color, 1.0);
}


