package com.project.imdang.ports.input.service;

import com.project.imdang.dto.detail.InsightDetailRequest;
import com.project.imdang.dto.detail.InsightDetailResponse;
import com.project.imdang.dto.list.InsightListRequest;
import com.project.imdang.dto.list.InsightListResponse;
import com.project.imdang.dto.preview.InsightPreviewRequest;
import com.project.imdang.dto.preview.InsightPreviewResponse;

public interface InsightQueryApplicationService {
    InsightListResponse list(InsightListRequest insightListRequest);
    InsightPreviewResponse preview(InsightPreviewRequest insightPreviewRequest);
    InsightDetailResponse detail(InsightDetailRequest insightDetailRequest);
}
