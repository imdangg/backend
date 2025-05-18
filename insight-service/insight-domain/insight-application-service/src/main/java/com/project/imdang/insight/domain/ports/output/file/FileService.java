package com.project.imdang.insight.domain.ports.output.file;

import com.project.imdang.common.domain.valueobject.File;

import java.io.IOException;

public interface FileService {
    String upload(File file) throws IOException;
}
