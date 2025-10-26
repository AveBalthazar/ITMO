clear all;
close all;

[~, scriptName] = fileparts(mfilename('fullpath'));
if ~isfolder(scriptName)
    mkdir(scriptName);
end

u = 1;

out = sim('model.slx','StopTime','6');
t = linspace(0, 999, 1000);
y = out.y;

u_t = figure;
plot(t, ones(size(t)), 'blue'), grid on
xlim([0, 6]);
xlabel('t'), ylabel('u(t)')
saveas(u_t, string(scriptName) + '\'+'u', 'epsc')

y_t = figure;
plot(y.Time, y.Data, 'blue'), grid on
xlabel('t'), ylabel('y(t)')
saveas(y_t, string(scriptName) + '\'+'y', 'epsc')