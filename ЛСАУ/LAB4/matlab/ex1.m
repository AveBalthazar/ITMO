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

fig_svob = figure;
out = sim('ex1/model.slx','StopTime','8');
y_model = out.y;
plot(y_model.Time, y_model.Data, LineWidth=1.2, DisplayName="$y_{raz}(t)$")
xlabel('Время'), ylabel('Амплитуда')
grid on
legend(Interpreter='latex', Location='best', BackgroundAlpha=.1, FontSize=12, FontName='Computer Modern')
saveas(fig_svob, string(scriptName) + '\' + 'razomk_fig' + '.eps', 'epsc')

k0 = 1;
k1 = 3; % траектория становится ограниченной при k1 = 1, k1 > 1 - система Ау, k1 < 1 - Ну

fig_regulator = figure;
out = sim('ex1/model_regulator.slx','StopTime','8');
y_model = out.y;
plot(y_model.Time, y_model.Data, LineWidth=1.2, DisplayName="$y_{zamk}(t)$")
xlabel('Время'), ylabel('Амплитуда')
grid on
legend(Interpreter='latex', Location='best', BackgroundAlpha=.1, FontSize=12, FontName='Computer Modern')
saveas(fig_regulator, string(scriptName) + '\' + 'zamk_fig' + '.eps', 'epsc')


% figure;
% num = [1];
% den = [1 -4 5];
% sys = tf(num, den);
% y = lsim(sys, u(:,2), t);
% plot(t, y)