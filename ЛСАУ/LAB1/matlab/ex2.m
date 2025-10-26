% clear all;
close all;

[~, scriptName] = fileparts(mfilename('fullpath'));
if ~isfolder(scriptName)
    mkdir(scriptName);
end

u = 1;

out = sim('model2_diagonal.slx','StopTime','6');
t = linspace(0, 6, 1000);
y_diagonal = out.y;

u_t = figure;
u1 = ones(size(t));
plot(t, u1, 'red'); grid on
xlim([0, 6]);
xlabel('t'), ylabel('u(t)')
saveas(u_t, string(scriptName) + '\'+'u', 'epsc')

num = [36 21 21];
den = [1 10 31 30];
sys = tf(num, den);
y = lsim(sys, u1, t);


y_t = figure;
plot(t, y, 'blue'); grid on
xlabel('t'), ylabel('y(t)')
hold on;

plot(y_diagonal.Time, y_diagonal.Data, '-.black')
hold on;

out = sim('model2_controlable.slx','StopTime','6');
y_controlable = out.y;
plot(y_controlable.Time, y_controlable.Data, '--red')
hold on;

xlim([0, 6]);
out = sim('model2_observable.slx','StopTime','6');
y_observable = out.y;
plot(y_observable.Time, y_observable.Data, ':green')
grid on;
legend('Мат. модель по передаточной функции', 'Мат. модель в диагональной форме', 'Мат. модель в управляемой форме', 'Мат. модель в наблюдаемой форме')
saveas(y_t, string(scriptName) + '\'+'y', 'epsc')
