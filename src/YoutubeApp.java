import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import classes.Canal;
import classes.Usuario;

/* Observer es un patrón de diseño de comportamiento que te permite 
definir un mecanismo de suscripción para notificar a varios objetos 
sobre cualquier  evento que le suceda al objeto que están observando. */

public class YoutubeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Canal> canales = new ArrayList<>(); // Lista para almacenar los canales
        List<Usuario> usuarios = new ArrayList<>(); // Lista para almacenar los usuarios

        // Mapa para las opciones del menú
        Map<Integer, Runnable> menuOptions = new HashMap<>();
        menuOptions.put(1, () -> agregarUsuario(scanner, usuarios));
        menuOptions.put(2, () -> agregarCanal(scanner, canales));
        menuOptions.put(3, () -> subirVideo(scanner, canales));
        menuOptions.put(4, () -> suscribirse(scanner, usuarios, canales));
        menuOptions.put(5, () -> cancelarSuscripcion(scanner, usuarios, canales));
        menuOptions.put(6, () -> salir());

        boolean running = true;

        while (running) {
            System.out.println("\n=== Menu de YouTube ===");
            mostrarCanales(canales); // Mostrar canales disponibles
            mostrarUsuarios(usuarios); // Mostrar usuarios disponibles

            // Menú reorganizado
            System.out.println("1. Agregar un Usuario");
            System.out.println("2. Agregar un Canal");
            System.out.println("3. Subir Video a un Canal");
            System.out.println("4. Suscribirse a un Canal");
            System.out.println("5. Cancelar Suscripción a un Canal");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            Runnable action = menuOptions.get(opcion);
            if (action != null) {
                action.run();
            } else {
                System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        scanner.close();
    }

    private static void agregarUsuario(Scanner scanner, List<Usuario> usuarios) {
        System.out.print("Introduce el nombre del nuevo usuario: ");
        String nombreNuevoUsuario = scanner.nextLine();

        // Verificar si el usuario ya existe
        if (buscarUsuario(usuarios, nombreNuevoUsuario) != null) {
            System.out.println("El usuario " + nombreNuevoUsuario + " ya existe. Intenta con otro nombre.");
            return;
        }

        usuarios.add(new Usuario(nombreNuevoUsuario));
        System.out.println("Usuario " + nombreNuevoUsuario + " agregado exitosamente.");
    }

    private static void agregarCanal(Scanner scanner, List<Canal> canales) {
        System.out.print("Introduce el nombre del nuevo canal: ");
        String nuevoNombreCanal = scanner.nextLine();

        // Verificar si el canal ya existe
        if (buscarCanal(canales, nuevoNombreCanal) != null) {
            System.out.println("El canal " + nuevoNombreCanal + " ya existe. Intenta con otro nombre.");
            return;
        }

        canales.add(new Canal(nuevoNombreCanal));
        System.out.println("Canal " + nuevoNombreCanal + " agregado exitosamente.");
    }

    private static void subirVideo(Scanner scanner, List<Canal> canales) {
        System.out.print("Introduce el nombre del canal: ");
        String nombreCanalSubir = scanner.nextLine();
        Canal canalParaSubir = buscarCanal(canales, nombreCanalSubir);
        if (canalParaSubir != null) {
            System.out.print("Introduce el título del video: ");
            String tituloVideo = scanner.nextLine();
            canalParaSubir.subirVideo(tituloVideo);
        } else {
            System.out.println("El canal no existe.");
        }
    }

    private static void suscribirse(Scanner scanner, List<Usuario> usuarios, List<Canal> canales) {
        System.out.print("Introduce el nombre del usuario que desea usar para suscribirse: ");
        String nombreUsuario = scanner.nextLine();
        Usuario usuarioSeleccionado = buscarUsuario(usuarios, nombreUsuario);

        if (usuarioSeleccionado != null) {
            System.out.print("Introduce el nombre del canal al que deseas suscribirte: ");
            String canalParaSuscribirse = scanner.nextLine();
            Canal canalSuscribirse = buscarCanal(canales, canalParaSuscribirse);
            if (canalSuscribirse != null) {
                usuarioSeleccionado.suscribirse(canalSuscribirse);
            } else {
                System.out.println("El canal no existe.");
            }
        } else {
            System.out.println("Usuario no encontrado. Asegúrate de haberlo registrado.");
        }
    }

    private static void cancelarSuscripcion(Scanner scanner, List<Usuario> usuarios, List<Canal> canales) {
        System.out.print("Introduce el nombre del usuario: ");
        String nombreUsuarioCancel = scanner.nextLine();
        Usuario usuarioCancel = buscarUsuario(usuarios, nombreUsuarioCancel);
        if (usuarioCancel != null) {
            System.out.print("Introduce el nombre del canal del que deseas cancelar la suscripción: ");
            String canalParaCancelar = scanner.nextLine();
            Canal canalCancelar = buscarCanal(canales, canalParaCancelar);
            if (canalCancelar != null) {
                usuarioCancel.desuscribirse(canalCancelar);
            } else {
                System.out.println("El canal no existe.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private static void salir() {
        System.out.println("Saliendo de la aplicación...");
        System.exit(0);
    }

    // Método para buscar un canal por nombre
    private static Canal buscarCanal(List<Canal> canales, String nombreCanal) {
        for (Canal canal : canales) {
            if (canal.getNombre().equalsIgnoreCase(nombreCanal)) {
                return canal;
            }
        }
        return null; // Si no se encuentra el canal
    }

    // Método para buscar un usuario por nombre
    private static Usuario buscarUsuario(List<Usuario> usuarios, String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombreUsuario)) {
                return usuario;
            }
        }
        return null; // Si no se encuentra el usuario
    }

    // Método para mostrar los canales disponibles
    private static void mostrarCanales(List<Canal> canales) {
        if (canales.isEmpty()) {
            System.out.println("No hay canales disponibles.");
        } else {
            System.out.println("\n Canales disponibles:");
            for (Canal canal : canales) {
                System.out.println("- " + canal.getNombre());
            }
        }
    }

    // Método para mostrar los usuarios disponibles
    private static void mostrarUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados. \n");
        } else {
            System.out.println("\n Usuarios registrados:");
            for (Usuario usuario : usuarios) {
                System.out.println("- " + usuario.getNombre());
            }
            System.out.println();
        }
    }
}