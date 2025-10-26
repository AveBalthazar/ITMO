% clear all;
close all;

[~, scriptName] = fileparts(mfilename('fullpath'));
if ~isfolder(scriptName)
    mkdir(scriptName);
end

t = (0:0.01:2)';
% u = [t, 1.5*ones(size(t))];
% u = [t, 0.6*t];
u = [t, cos(t*2)];
% u_str = '1.5';
% u_str = '0.6t';
u_str = 'cos2t';
yy0 = 0;

for a_1 = [7.8]
    y_t = figure;
    a_0 = 64.21;
    for y0 = [-1 0 1]
        out = sim('ex1/model.slx','StopTime','2');
        y_model = out.y;
        y_model.plot(DisplayName="$y(0) = " + string(y0) + ", \dot{y}(0) = " + string(yy0) + "$", LineWidth=1.2)
        hold on;
    end
    legend(BackgroundAlpha=.99, Interpreter='latex', Location='best')
    grid on;
    % ylim([-5, 6.5])
    title('$\ddot{y} + (' + string(a_1) + ')\dot{y} + (' + string(a_0) + ')y = ' + string(a_0) + 'u, u(t) = '+ u_str + '$', 'Interpreter', 'latex', FontWeight='normal')
    xlabel('t'), ylabel('y(t)')
    % ylim([-1.5, 1.5]);
    saveas(y_t, string(scriptName) + '\'+u_str+'_' + string(a_1) + '_' + string(a_0) + '.eps', 'epsc')
end

