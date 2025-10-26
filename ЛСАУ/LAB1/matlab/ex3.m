% clear all;
close all;

[~, scriptName] = fileparts(mfilename('fullpath'));

t = (0:0.01:10);
u1 = ones(size(t));
u2 = sin(t);
out = sim('model3');

y = figure;
plot(out.y1.Time, out.y1.Data, 'blue')
xlabel('t'); ylabel('y(t)')
hold on;

plot(out.y2.Time, out.y2.Data, 'red')
grid on;
legend('$y_1(t)$', '$y_2(t)$', 'Interpreter', 'latex', BackgroundAlpha=.0)
ylim([-1, 8]);
saveas(y, string(scriptName) + '\'+'y', 'epsc')

u = figure;
plot(t, u1, 'blue')
hold on;
plot(t, u2, 'red')
xlabel('t'); ylabel('u(t)')
grid on
ylim([-1.5, 1.5])
legend('$u_1(t)$', '$u_2(t)$', 'Interpreter', 'latex', BackgroundAlpha=.0)
saveas(u, string(scriptName) + '\'+'u', 'epsc')