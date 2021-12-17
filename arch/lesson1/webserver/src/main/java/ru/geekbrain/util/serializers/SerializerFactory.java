package ru.geekbrain.util.serializers;

public class SerializerFactory {
    public static Serializer getSerializer(){
        return new ResponseSerializer();
    }
}
