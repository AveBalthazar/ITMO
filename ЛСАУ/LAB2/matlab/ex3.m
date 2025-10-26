clear all;
close all;

t = (0:0.001:3)';
y_ref = cos(5*t)+exp(t)+exp(-5*t);

A = [1, 0, 0, 0;
    0, -5, 0, 0;
    0, 0, 0, 5;
    0, 0, -5, 0];
C = [1, 1, 2, 2];
x_0 = [1; 1; 0.25; 0.25];
u = zeros(size(x_0));
out = sim('ex3/generator.slx','StopTime','3');
y_model = out.y;

plot(t, y_ref, 'black', LineWidth=1.2);
hold on;
y_model.plot('r--', LineWidth=1.2);
grid on;
legend('g_{ж}(t)', 'g(t)', Interpreter='tex');
xlabel('t'); ylabel('y');

e = y_ref - y_model.Data;
figure();
plot(y_model.Time, e, LineWidth=1.2)
grid on;
legend('e = g_{ж}(t)-g(t)', Interpreter='tex');
xlabel('t'); ylabel('e');
