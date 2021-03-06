package com.widle.coinscap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by imperial-web on 5/5/2018.
 */

public class ObjectSerializer {
    private ObjectSerializer() {}

    public static Optional<byte[]> serialize(Object object) {
        return serialize(object, EMPTY_EXCEPTION_CONSUMER, EMPTY_BYTES_CONSUMER);
    }

    public static Optional<byte[]> serialize(Object object, Consumer<? super IOException> exceptionConsumer) {
        return serialize(object, exceptionConsumer, EMPTY_BYTES_CONSUMER);
    }

    public static Optional<byte[]> serialize(Consumer<byte[]> bytesConsumer, Object object) {
        return serialize(object, EMPTY_EXCEPTION_CONSUMER, bytesConsumer);
    }

    public static Optional<byte[]> serialize(Object object, Consumer<? super IOException> exceptionConsumer, Consumer<byte[]> bytesConsumer) {
        try (final ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            try (final ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeObject(object) ;
                oos.flush();
            }

            final byte[] bytes = bos.toByteArray();
            bytesConsumer.accept(bytes);
            return Optional.of(bytes);
        }
        catch (IOException e) {
            exceptionConsumer.accept(e);
            return Optional.empty();
        }
    }

    public static Optional<Object> deserialize(byte[] bytes) {
        return deserialize(bytes, EMPTY_EXCEPTION_CONSUMER);
    }

    public static Optional<Object> deserialize(byte[] bytes, Consumer<? super Exception> exceptionConsumer) {
        return deserialize(Object.class, bytes, exceptionConsumer, EMPTY_OBJECT_CONSUMER);
    }

    public static <T> Optional<T> deserialize(Class<T> objectClass, Consumer<? super T> objectConsumer, byte[] bytes) {
        return deserialize(objectClass, bytes, EMPTY_EXCEPTION_CONSUMER, objectConsumer);
    }

    public static <T> Optional<T> deserialize(Class<T> objectClass, byte[] bytes) {
        return deserialize(objectClass, bytes, EMPTY_EXCEPTION_CONSUMER, EMPTY_OBJECT_CONSUMER);
    }

    public static <T> Optional<T> deserialize(Class<T> objectClass, byte[] bytes, Consumer<? super Exception> exceptionConsumer) {
        return deserialize(objectClass, bytes, exceptionConsumer, EMPTY_OBJECT_CONSUMER);
    }

    public static <T> Optional<T> deserialize(Class<T> objectClass, byte[] bytes, Consumer<? super Exception> exceptionConsumer, Consumer<? super T> objectConsumer) {
        try (final ByteArrayInputStream bis = new ByteArrayInputStream(bytes); final ObjectInputStream ois = new ObjectInputStream(bis)) {
            final T object = objectClass.cast(ois.readObject());
            objectConsumer.accept(object);
            return Optional.of(object);
        }
        catch (IOException | ClassNotFoundException e) {
            exceptionConsumer.accept(e);
            return Optional.empty();
        }
    }

    public static final Consumer<byte[]> EMPTY_BYTES_CONSUMER = bytes -> {};

    public static final Consumer<Object> EMPTY_OBJECT_CONSUMER = object -> {};

    public static final Consumer<Exception> EMPTY_EXCEPTION_CONSUMER = e -> {};
}
