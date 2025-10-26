a_0 = 6.25;
a_1 = 5;

A = [0 1; -a0 -a1];
B = [0; 1];
C = [1 0];
D = 0;

sys = ss(A,B,C,D);

t = 0:0.01:6;
u = sin(t);     % вход
X0 = [1; 0];           % y(0)=1, y'(0)=0

[y,t,x] = lsim(sys,u,t,X0);

initial(sys,[1 0],t)
plot(t,y), grid on
xlabel('t'), ylabel('y(t)')
