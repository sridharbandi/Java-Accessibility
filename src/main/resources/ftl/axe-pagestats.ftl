<#list issues as issue>
    <div class="col-sm-6 pillspage badge badge-secondary">
        <h5>${issue.name}</h5>
        <h6 class="text-truncate">${issue.url}</h6>
        <div class="time">
            <span class="text-warning"><i class="fas fa-clock"></i> ${issue.date}</span>
        </div>
        <div class="row justify-content-center">
            <div class="col-4">
                <span class="specs">ENGINE : </span>
                <span class="badge badge-success">${issue.engine}</span>
            </div>

            <div class="col-4">
                <span class="specs">BROWSER SIZE : </span>
                <span class="badge badge-success">${issue.size}</span>
            </div>
        </div>
        <div class="row justify-content-center pagespechold">
            <div class="col-2 pagespec badge badge-danger">${issue.critical}</div>
            <div class="col-2 pagespec badge badge-warning">${issue.serious}</div>
            <div class="col-2 pagespec badge badge-info">${issue.moderate}</div>
            <div class="col-2 pagespec badge badge-light">${issue.minor}</div>
        </div>
        <div class="row">
            <div class="col viewreport">
                <a href="${issue.reportID}.html" class="btn btn-outline-light btn-sm" role="button">View Report</a>
            </div>
        </div>
    </div>
</#list>