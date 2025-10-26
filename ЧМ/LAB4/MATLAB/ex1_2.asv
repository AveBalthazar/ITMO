clear all;
close all;

path_to_graphs = "FM-LAB4" + filesep + "ex1_2" + filesep;

if ~exist(path_to_graphs, 'dir')
    mkdir(path_to_graphs);
end

T = 1000;
dt = 0.001;
t = -T/2:dt:T/2;
N = length(t);
W = (1) / dt;
dw = 1 / T;
omega = (-W/2:dw:W/2) * 2 * pi;

a = 3;
t1 = -2;
t2 = 2;
g = zeros(size(t));
g(t >= t1 & t <= t2) = a;  % g(t)

b = 0;
c = 1;
d = 4;
xi = 2 * rand(size(t)) - 1;

u = g + b * xi + c * sin(d * t);  % u(t)

% Параметры фильтра второго порядка
a1 = 0;
a2 = 25;
b1 = sqrt(a2)*2.1;
b2 = a2;

coeffs_text = 'a1=' + string(a1) + '_a2=' + string(a2) + '_b1=' + string(b1) + '_b2=' + string(b2) + '_d=' + string(d) + '_c=' + string(c);

if ~exist(path_to_graphs + coeffs_text, 'dir')
    mkdir(path_to_graphs + coeffs_text);
end

% Проверка устойчивости (корни полинома знаменателя)
p_roots = roots([1, b1, b2]);  % Корни полинома знаменателя
disp('Корни полинома знаменателя:');
disp(p_roots);
if all(real(p_roots) < 0)
    disp('Фильтр устойчив.');
else
    disp('Фильтр неустойчив!');
end

W2 = tf([1, a1, a2], [1, b1, b2]);

[y, t_out] = lsim(W2, u, t);


G = fftshift(fft(g)) / sqrt(N) / sqrt(2*pi);
U = fftshift(fft(u)) / sqrt(N);
Y = fftshift(fft(y)) / sqrt(N) / sqrt(2*pi);

graphs = [];

% 1 АЧХ фильтра второго порядка
graphs(end+1) = figure('Name', 'АЧХ');
w2 = freqs([1, a1, a2], [1, b1, b2], t);
phase = abs(w2);
angle = angle(w2);
minimum = min(phase);
index_min = find(phase == minimum);
t_min = max(t(index_min));
plot(t, phase, 'MarkerIndices', index_min);
hold on; 
plot(t_min, minimum, "r*")
xlim([0, max(t)])
xticks(0:max(t)/10:max(t))
grid on;
legend({'$|W_2 (i\omega)|$'}, 'Interpreter', 'latex', 'fontsize', 11);
text(t_min - 0.05*max(t), 0.05, ['(', num2str(t_min), ', ', num2str(minimum), ')'], 'FontSize', 12, 'FontName', 'serif');
xlabel('Угловая частота');
ylabel('Амплитуда');
legend(BackgroundAlpha=.0);

% 2 Графики исходного, зашумленного и фильтрованного сигналов
graphs(end+1) = figure('Name', 'Графики исходного, зашумлённого, фильтрованного сигналов');
plot(t, y, 'r'); hold on;
plot(t, u, 'b');
plot(t, g, 'black');
grid on;
legend('Фильтрованный сигнал y(t)', 'Зашумлённый сигнал u(t)', 'Исходный сигнал g(t)');

significant_indices = find(abs(y) > dt);
t_max = round(t(significant_indices(end)));

% xlim([t1 - 2, t_max]);
% xticks(-abs(t_max):2:t_max)
xlim([t1 - 8, t2 + 8])
ylim([-2, round(max(u) + max(u)/3)]);
xlabel('Время');
ylabel('Амплитуда');
legend(BackgroundAlpha=.6);

% Расчёт спектра произведения частотной передаточной функции фильтра и спектра u(t)
W2_freq = (-(omega.^2) + a1 * 1i * omega + a2) ./ (-(omega.^2) + b1 * 1i * omega + b2);
% W2_back = ifft(ifftshift(W2_freq)) / sqrt(2 * pi);
Y_filter = W2_freq .* U;  % Моделируем частотный спектр фильтрованного сигнала
y_filter = ifft(ifftshift(Y_filter)) * sqrt(N);

% 3 График фильтрованного сигнала и обратного преобразования Фурье от произведения
graphs(end+1) = figure('Name', 'y(t) и произведение');
plot(t, y, 'b'); hold on;
plot(t, y_filter, 'g');
grid on;

ylim([-1, 4]);
% xlim([t1 - 2, t_max]);
xlim([t1 - 8, t2 + 8])
legend({'$y(t)$', '$\mathcal{F}^{-1}\{W_2(i\omega) \cdot \hat{u}(\omega)\}$'}, 'Interpreter', 'latex', 'fontsize', 11);
xlabel('Время');
ylabel('Амплитуда');
legend(BackgroundAlpha=.0);

% 4 Спектры
graphs(end+1) = figure('Name', 'Графики спектров зашумлённого и фильтрованного сигнала');
plot(omega, abs(U), 'b'); hold on;
plot(omega, abs(Y), 'r');
plot(omega, abs(G), 'black');
grid on;
ylim([0, 12])
xlim([-3*d, 3*d]);
legend('$|\hat{u}(\omega)|$', '$|\hat{y}(\omega)|$', '$|\hat{g}(\omega)|$', 'Interpreter', 'latex', 'fontsize', 11);
xlabel('Угловая частота');
ylabel('Амплитуда');
legend(BackgroundAlpha=.6);

% 5 График модуля Фурье-образа фильтрованного сигнала и произведения
graphs(end+1) = figure('Name', 'Спектры y(t) и произведения');
plot(omega, abs(Y), 'b'); hold on;
plot(omega, abs(Y_filter) / sqrt(2 * pi), 'g');
grid on;
ylim([0, 6]);
% xlim([-d-d/2, d+d/2]);
xlim([-3*d, 3*d]);
% xticks(-d-d/2:1:d+d/2)
legend({'$\left|\mathcal{F}\{y(t)\}\right|$', '$\left|\mathcal{F}\{W_2(i\omega) \cdot \hat{u}(\omega)\}\right|$'}, 'Interpreter', 'latex', 'fontsize', 11);
xlabel('Угловая частота');
ylabel('Амплитуда');
legend(BackgroundAlpha=.0);

% Исследование влияния параметров c и d
% figure;
% c_values = [0, 5, 10];
% d_values = [10, 20, 30];
% for i = 1:length(c_values)
%     for j = 1:length(d_values)
%         xi = 2 * rand(size(t)) - 1;
%         u = g + b * xi + c_values(i) * sin(d_values(j) * t);
%         % Пропускание сигнала через фильтр
%         [y, ~] = lsim(W2, u, t);
% 
%         subplot(length(c_values), length(d_values), (i-1)*length(d_values) + j);
%         plot(t, u, 'r', 'LineWidth', 1.5); hold on;
%         plot(t, y, 'b', 'LineWidth', 1.5);
%         xlim([-5, 5])
%         title(['c = ', num2str(c_values(i)), ', d = ', num2str(d_values(j))]);
%         xlabel('Время (t)');
%         ylabel('Амплитуда');
%     end
% end

for n = 1:length(graphs)
    save_path = path_to_graphs + coeffs_text + filesep + "h" + string(n) + ".png";
    saveas(graphs(n), save_path, "png")
end