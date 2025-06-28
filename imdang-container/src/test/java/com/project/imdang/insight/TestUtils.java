package com.project.imdang.insight;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class TestUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false); // 유니코드 방지

    public static void printPrettyJson(String rawJson) {
        try {
            Object json = objectMapper.readValue(rawJson, Object.class);
            String pretty = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

            // UTF-8 강제 지정한 출력 스트림
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);
            writer.println("==== Pretty JSON ====");
            writer.println(pretty);
        } catch (Exception e) {
            System.out.println("JSON 파싱 실패: " + e.getMessage());
        }
    }
}
