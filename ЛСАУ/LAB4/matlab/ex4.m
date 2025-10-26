% clear all;
close all;

[~, scriptName] = fileparts(mfilename('fullpath'));
if ~isfolder(scriptName)
    mkdir(scriptName);
end

t = (0:0.01:8)';

% g_strs = {'g_a', 'g_vt', 'g_at2', 'g_asinwt'};
g_strs = {'g_a', 'g_vt', 'g_at2'};
g_latexs = {'1', 't', '0.45t^2', '\sin{(0.45 t)}'};
g_variants = [ones(size(t)), t, 0.45.*t.^2, sin(0.45.*t)];

for i = 1:numel(g_strs)
    g = [t, g_variants(:, i)];
    g_str = g_strs{i};
    g_latex = g_latexs{i};
    for k = [0.5, 1, 3]
        fig_regulator = figure;
        hold on; grid on;

        out = sim('ex4/model_regulator4.slx','StopTime','8');
        y_model = out.y;
        error = out.e;
        plot(t, g(:,2), LineWidth=1.2, DisplayName="$g(t) = " + g_latex + "$")
        plot(y_model.Time, y_model.Data, LineWidth=1.2, DisplayName="$y_{zamk}(t), k = " + string(k) + "$")
        xlabel('Время'), ylabel('Амплитуда')
        legend(Interpreter='latex', Location='best', BackgroundAlpha=.3, FontSize=12, FontName='Computer Modern')

        fig_error = figure;
        plot(error.Time, error.Data, LineWidth=1.2, DisplayName="$e(t)$")
        grid on; hold on;
        if (i == 2)
            e_steady = error.Data(end);
            xl = xlim;                     % текущие пределы по X — вектор [x_min, x_max]
            plot(xl, [e_steady, e_steady], 'k-.', Color='black', DisplayName="$e_{end} = " + string(e_steady) + "$");     % два значения y0 для двух точек
        end
        legend(Interpreter='latex', Location='best', BackgroundAlpha=.3, FontSize=12, FontName='Computer Modern')
        xlabel('Время'), ylabel('Ошибка')
        saveas(fig_regulator, string(scriptName) + '\k' + string(k) + '_' + g_str + '.eps', 'epsc')
        saveas(fig_error, string(scriptName) + '\k' + string(k) + '_' + g_str + '_error.eps', 'epsc')
    end
end