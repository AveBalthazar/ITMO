% clear all;
close all;

[~, scriptName] = fileparts(mfilename('fullpath'));
if ~isfolder(scriptName)
    mkdir(scriptName);
end

t = (0:0.001:10)';
u = [t, ones(size(t)), sin(t)];

out = sim('model4');
y_t = figure;
plot(out.y.Time, out.y.Data(:, 1), 'blue')
hold on;
plot(out.y.Time, out.y.Data(:, 2), 'red')
grid on;
legend('$y_1(t)$', '$y_2(t)$', 'Interpreter', 'latex')
saveas(y_t, string(scriptName) + '\'+'y', 'epsc')

u_t = figure;
plot(t, u(:, 2), 'blue')
hold on;
plot(t, u(:, 3), 'red')
grid on;
ylim([-1.5, 1.5])
legend('$u_1(t)$', '$u_2(t)$', 'Interpreter', 'latex')
saveas(u_t, string(scriptName) + '\'+'u', 'epsc')