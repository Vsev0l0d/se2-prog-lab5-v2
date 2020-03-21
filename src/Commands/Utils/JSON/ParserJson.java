package Commands.Utils.JSON;

import BasicClasses.Flat;
import Collection.CollectionManager;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Класс, отвечающий за сохранение и загрузку JSON.
 */
public class ParserJson {
    private static String filePath = System.getenv("WORK_FILE_PATH");
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder
            .registerTypeAdapter(ZonedDateTime.class, new TypeAdapter<ZonedDateTime>() {
                @Override
                public void write(JsonWriter out, ZonedDateTime value) throws IOException {
                    out.value(value.toString());
                }

                @Override
                public ZonedDateTime read(JsonReader in) throws IOException {
                    return ZonedDateTime.parse(in.nextString());
                }
            })
            .serializeNulls()
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .create();

    public static void collectionToJson() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(CollectionManager.getCollection(), writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void fromJsonToCollection() {
        if (filePath != null) {
            try (Reader reader = new FileReader(filePath)) {
                CollectionManager.initSet();
                List<Flat> flats = gson.fromJson(reader, new TypeToken<List<Flat>>(){}.getType());
                if (flats.size() > 0) for (Flat flat: flats) { CollectionManager.addJsonObject(flat); }
                System.out.println("Коллекция загружена.");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (SecurityException e) {
                System.out.println("Недостаточно прав для открытия файла.");
            } catch (NullPointerException e) {
                System.out.println("В файле нет объектов");
            } catch (com.google.gson.JsonSyntaxException e){
                System.out.println("Ошибка в содержании файла: " + e.getMessage());
                System.out.println("Создана пустая коллекция.");
            }
        } else { System.out.println("Переменная окружения не выставлена"); }
    }
}
