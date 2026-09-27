# Dibuixa les trajectòries d'un fitxer generat amb el plotter Offline.
# Ús (des de la carpeta nbody1):
#   python -i src/solution/offline_plot.py choreography.txt
import sys
import numpy as np
import matplotlib.pyplot as plt

fname = sys.argv[1] if len(sys.argv) > 1 else 'choreography.txt'
with open(fname, 'r') as f:
    data = f.readlines()

num_bodies = int(data[0])
x = []
y = []
for d in data[1:]:
    p, q = [float(c) for c in d.split()]
    x.append(p)
    y.append(q)

x = np.array(x)
y = np.array(y)
x = np.reshape(x, (len(x) // num_bodies, num_bodies))
y = np.reshape(y, (len(y) // num_bodies, num_bodies))

plt.close('all')
plt.figure()
for nb in range(num_bodies):
    plt.plot(x[:, nb], y[:, nb], '-')
    plt.plot(x[-1, nb], y[-1, nb], 'k.')  # punt final

plt.axis('equal')
plt.title(fname.replace('\\', '/').split('/')[-1])
plt.show()
