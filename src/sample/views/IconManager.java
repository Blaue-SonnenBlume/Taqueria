package sample.views;

import java.util.HashMap;
import java.util.Map;

public class IconManager {
    // Mapa para asociar nombres de bebidas con URL de iconos
    private static final Map<String, String> bebidaIconMap = new HashMap<>();

    // Inicializar el mapa con los nombres de bebidas y las URL de los iconos correspondientes
    static {
        bebidaIconMap.put("coca cola", "https://cdn-icons-png.flaticon.com/128/14369/14369808.png");
        bebidaIconMap.put("fanta", "https://cdn-icons-png.flaticon.com/128/14461/14461866.png");
        bebidaIconMap.put("pepsi", "https://cdn-icons-png.flaticon.com/128/14369/14369818.png");
        bebidaIconMap.put("jugo", "https://cdn-icons-png.flaticon.com/128/1047/1047473.png");
        bebidaIconMap.put("tea", "https://cdn-icons-png.flaticon.com/128/1764/1764316.png");
        bebidaIconMap.put("agua", "https://cdn-icons-png.flaticon.com/128/824/824239.png");
        bebidaIconMap.put("sprite", "https://cdn-icons-png.flaticon.com/128/7511/7511888.png");
        bebidaIconMap.put("cafe", "https://cdn-icons-png.flaticon.com/128/10847/10847468.png");
        // Agrega más bebidas según sea necesario
    }

    // Método para obtener la URL del icono de una bebida específica
    public static String getIconURL(String nombreBebida) {
        return bebidaIconMap.getOrDefault(nombreBebida, "https://cdn-icons-png.flaticon.com/128/1249/1249169.png");
    }
}
