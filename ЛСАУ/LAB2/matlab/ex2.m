% clear all;
close all;
t = (0:0.01:10)';
g = [t, ones(size(t))];
T1 = 0.4;
T2 = 1;
K = 0.5;
out = sim('ex2/model.slx','StopTime','10');

num = [K];
den = [T1*T2 (T1 + T2) 1 K];
sys = tf(num, den);
y = lsim(sys, g(:,2), t);

figure()
% out.y.plot()
hold on;
plot(t, y, 'black', LineWidth=1.2)
xlabel('t'); ylabel("y");
legend('y(t)')
grid on;