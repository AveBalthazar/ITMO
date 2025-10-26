close all;

path_to_graphs = "FM-LAB4" + filesep + "ex1_1" + filesep;

if ~exist(path_to_graphs, 'dir')
    mkdir(path_to_graphs);
end

T = 100;
dt = 0.0001;
t = -T/2:dt:T/2;
N = length(t);
V = 1/dt;
dv = 1/T;
v = -V/2:dv:V/2;
a = 3;

g = zeros(size(t));
g(t >= -2 & t <= 2) = a;  % g(t)

b = 1;
c = 0;
d = 1;
xi = 2 * rand(size(t)) - 1;

u = g + b * xi + c * sin(d * t);  % u(t)
    

T_filter = 0.001;  % Постоянная времени фильтра
W1 = tf(1, [T_filter, 1]);  % Передаточная функция фильтра


coeffs_text = 'a=' + string(a) + '_T=' + string(T_filter);

if ~exist(path_to_graphs + coeffs_text, 'dir')
    mkdir(path_to_graphs + coeffs_text);
end

[y, t_out] = lsim(W1, u, t);


G = fftshift(fft(g)) / sqrt(2*pi) / sqrt(N);
U = fftshift(fft(u)) / sqrt(2*pi) / sqrt(N);
Y = fftshift(fft(y)) / sqrt(2*pi) / sqrt(N);

f = linspace(-V/2, V/2, N);
omega = 2 * pi * f;
graphs = [];
% 1 Графики исходного, зашумленного и фильтрованного сигналов
graphs(end+1) = figure('Name', 'Графики исходного, зашумлённого, фильтрованного сигналов');
plot(t, u, 'r'); hold on;
plot(t, y, 'b');
plot(t, g, 'black');
grid on;
legend('Зашумлённый сигнал u(t)', 'Фильтрованный сигнал y(t)', 'Исходный сигнал g(t)');
xlim([-5, 5]);
xticks(-5:1:5);
ylim([-5, 2]);
xlabel('Время');
ylabel('Амплитуда');
legend(BackgroundAlpha=.6);

% Расчёт спектра произведения частотной передаточной функции фильтра и спектра u(t)
W1_freq = 1 ./ (T_filter * 1i * 2 * pi * f + 1);  % Частотная передаточная функция
Y_filter = W1_freq .* U;  % Моделируем частотный спектр фильтрованного сигнала
y_filter = ifft(ifftshift(Y_filter)) * sqrt(N) * sqrt(2 * pi); 

% 2 График фильтрованного сигнала и обратного преобразования Фурье от произведения
graphs(end+1) = figure('Name', 'y(t) и произведение');
plot(t, y, 'b'); hold on;
plot(t, y_filter, 'g');
grid on;
ylim([-4, 1]);
xlim([-4, 4]);
legend({'$y(t)$', '$\mathcal{F}^{-1}\{W_2(i\omega) \cdot \hat{u}(\omega)\}$'}, 'Interpreter', 'latex', 'fontsize', 11);
xlabel('Время');
ylabel('Сигнал');
legend(BackgroundAlpha=.0);

% 3 Модули Фурье-образа исходного, зашумленного и фильтрованного
graphs(end+1) = figure('Name', 'Графики спектров исходного, зашумлённого и фильтрованного сигнала');
plot(omega, abs(U), 'r'); hold on;
plot(omega, abs(Y), 'b');
plot(omega, abs(G), 'black');
xticks(-15:5:15);
grid on;
xlim([-15, 15]);
legend('$|\hat{u}(\omega)|$', '$|\hat{y}(\omega)|$', '$|\hat{g}(\omega)|$', 'Interpreter', 'latex', 'fontsize', 11);
xlabel('Угловая частота');
ylabel('Амплитуда');
legend(BackgroundAlpha=.6);

% 4 График спектра фильтрованного сигнала и спектра произведения
graphs(end+1) = figure('Name', 'Спектры y(t) и произведения');
plot(omega, abs(Y), 'b'); hold on;
plot(omega, abs(Y_filter), 'g');
grid on;
xlim([-15, 15]);
legend({'$\left|\mathcal{F}\{y(t)\}\right|$', '$\left|\mathcal{F}\{W_2(i\omega) \cdot \hat{u}(\omega)\}\right|$'}, 'Interpreter', 'latex', 'fontsize', 11);
xlabel('Угловая частота');
ylabel('Амплитуда');
legend(BackgroundAlpha=.0);

% 5 АЧХ фильтра
graphs(end+1) = figure('Name', 'АЧХ');
phase = abs(freqs(1, [T_filter, 1], 2 * pi * f));
plot(omega, phase);
xlim([0, max(omega)])
% freqs(1, [T_filter, 1], 2 * pi * f)
grid on;
legend({'$|W_1 (i\omega)|$'}, 'Interpreter', 'latex', 'fontsize', 11);
legend(BackgroundAlpha=.0);
xlabel('Угловая частота');
ylabel('Амплитуда');

for n = 1:length(graphs)
    save_path = path_to_graphs + coeffs_text + filesep + "h" + string(n) + ".png";
    saveas(graphs(n), save_path, "png")
end