% clear all;
close all;
t = (0:0.001:6)';
a_0 = -0.36;
a_1 = 0;
y0 = 0;
yy0 = 0.1;

out = sim('ex1/model.slx','StopTime','6');
y_model = out.y;
y_t = figure;

y_model.plot('black', LineWidth=1.2)
grid on;
title('y_{св}(t)', 'Interpreter', 'tex', FontWeight='normal')
hold on;

% f = exp(-2.5.*t)+2.5.*t.*exp(-2.5.*t);
% f = 0.125.*sin(8.*t).*exp(-t) + cos(8.*t).*exp(-t);
% f = cos(8.*t);
% f = (1/20).*exp(t).*cos(8.*t)-(1/160).*exp(t).*sin(8.*t);
% f = (1/20 - t./8).*exp((2.5).*t);
f = (1/12).*exp(0.6.*t)-(1/12).*exp(-0.6.*t);
plot(t, f, '--red', LineWidth=1.2);
xlabel('t'), ylabel('y(t)')
ylim([-1.5, 1.5]);
legend('Моделирование системы', 'Аналитическое решение', BackgroundAlpha=.0)