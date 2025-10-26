% clear all;
close all;

[~, scriptName] = fileparts(mfilename('fullpath'));
if ~isfolder(scriptName)
    mkdir(scriptName);
end

t = (0:0.01:8)';
% u = [t, 1.5*ones(size(t))];
% u = [t, 0.6*t];
u = [t, zeros(size(t))];

T = 0.01;

k0 = 1;
k1 = 3; % траектория становится ограниченной при k1 = 1, k1 > 1 - система Ау, k1 < 1 - Ну

fig_regulator = figure;
hold on; grid on;

out = sim('ex1/model_regulator.slx','StopTime','8');
y_model = out.y;
plot(y_model.Time, y_model.Data, LineWidth=1.5, DisplayName="$y_{zamk}(t)$", Color='black')

out = sim('ex2/model_regulator2.slx','StopTime','8');
y_model = out.y;
plot(y_model.Time, y_model.Data, LineWidth=1.3, DisplayName="$y_{z}(t), T = " + string(T) + "$")
T = 0.4;

out = sim('ex2/model_regulator2.slx','StopTime','8');
y_model = out.y;
plot(y_model.Time, y_model.Data, LineWidth=1.3, DisplayName="$y_{z}(t), T = " + string(T) + "$")

T = 0.2;
out = sim('ex2/model_regulator2.slx','StopTime','8');
y_model = out.y;
plot(y_model.Time, y_model.Data, LineWidth=1.3, DisplayName="$y_{z}(t), T = " + string(T) + "$")

T = 0.1;
out = sim('ex2/model_regulator2.slx','StopTime','8');
y_model = out.y;
plot(y_model.Time, y_model.Data, LineWidth=1.3, DisplayName="$y_{z}(t), T = " + string(T) + "$")

xlabel('Время'), ylabel('Амплитуда')
ylim([-2, 3])
legend(Interpreter='latex', Location='best', BackgroundAlpha=.3, FontSize=12, FontName='Computer Modern')
saveas(fig_regulator, string(scriptName) + '\' + string(T) + '.eps', 'epsc')
