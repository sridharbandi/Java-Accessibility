<#ftl output_format="HTML">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <style>
        .pills, .pillspage {
            border: 5px solid #424242
        }

        body {
            background-color: #424242;
            font-family: Roboto, sans-serif
        }

        .header {
            color: #ffc400;
            text-align: center;
            margin: 20px
        }

        .pills {
            padding: 35px 5px
        }

        .pillspage {
            padding: 15px 5px
        }

        .specs {
            font-size: 10px !important
        }

        .time {
            margin-bottom: 10px !important
        }

        .pagespechold {
            padding-top: 12px
        }

        .pagespec {
            border: 2px solid #6c757d;
            padding: 8px 5px;
            font-size: 18px
        }

        .viewreport {
            padding-top: 10px
        }

        hr {
            margin-top: 0 !important;
            margin-bottom: .5em !important
        }

        .btncollapse {
            padding: 5px
        }

        .tab-content > .tab-pane {
            margin-top: 10px;
        }

        .issuenode {
            margin-left: 60px;
            margin-bottom: 5px
        }

        .alert {
            margin: 5px !important
        }

        .alert, .badge, .btn, .card {
            border-radius: 0 !important
        }

        .footer {
            min-height: 60px
        }

        .nav-tabs .nav-item.show .nav-link, .nav-tabs .nav-link.active {
            color: #ffc400;
            background-color: dimgrey;
            border-color: transparent transparent #f3f3f3;
            border-bottom: 6px solid #eee !important;
            font-size: 20px;
        }

        .nav-tabs .nav-link {
            border: none !important;
            border-top-left-radius: .25rem;
            border-top-right-radius: .25rem;
            color: #ffc400;
            font-size: 20px;
        }
    </style>
    <title>Report</title>
</head>
<body>
<#include 'heading.ftl'>
<div class="container">
    <br>
    <h3 class="text-white text-center">Page Statistics</h3>
    <#include 'axe-pagemetrics.ftl'>
    <br>
    <h3 class="text-white text-center">Issues - Details</h3>
    <nav>
        <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
            <#list results?keys as key>
                <a class="nav-item nav-link <#if key?is_first>active</#if>" id="${key}-tab" data-toggle="tab"
                   href="#${key}" role="tab"
                   aria-controls="${key}" aria-selected="<#if key?is_first>true<#else>false</#if>">${key?cap_first}
                    &nbsp;<span
                            class="badge badge-light"> ${results[key].total}</span></a>
            </#list>
        </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
        <#assign colorMap = { "critical":"danger", "serious":"warning", "moderate":"info", "minor":"light" }>
        <#list results?keys as key>
            <div class="tab-pane fade <#if key?is_first>show active</#if>" id="${key}" role="tabpanel"
                 aria-labelledby="${key}-tab">
                <nav>
                    <div class="nav nav-tabs" id="nav-tab${key}" role="tablist">
                        <#list results[key].stats?keys as stats>
                            <a class="nav-item nav-link text-${colorMap['${stats}']} <#if stats?is_first>active</#if>"
                               id="${key}${stats}-tab"
                               data-toggle="tab"
                               href="#${key}${stats}" role="tab"
                               aria-controls="${key}${stats}"
                               aria-selected="<#if stats?is_first>true<#else>false</#if>">${stats?cap_first}&nbsp;<span
                                        class="badge badge-${colorMap['${stats}']}"> ${results[key].stats[stats]}</span></a>
                        </#list>
                    </div>
                </nav>
                <div class="tab-content" id="nav-tab${key}Content">
                    <#list ['critical', 'serious', 'moderate', 'minor'] as issueType>
                        <div class="tab-pane fade <#if issueType?is_first>show active</#if>" id="${key}${issueType}"
                             role="tabpanel"
                             aria-labelledby="${key}${issueType}-tab">
                            <#list results[key].issueList?filter(x -> x.impact == issueType) as issues>
                                <div class="card bg-${colorMap['${issueType}']}">
                                    <div class="alert alert-${colorMap['${issueType}']} btncollapse" role="alert">
                                        <#list ['description', 'help', 'helpUrl', 'id', 'nodes'] as issuekey>
                                            <#if issuekey=='nodes'>
                                                <div><strong>${issuekey?cap_first} :</strong></div>
                                                <#list issues[issuekey] as node>
                                                    <div class="card bg-${colorMap['${issueType}']} issuenode">
                                                        <div class="alert alert-${colorMap['${issueType}']} btncollapse"
                                                             role="alert">
                                                            <div><strong>Summary
                                                                    : </strong>${node.failureSummary}</div>
                                                            <hr/>
                                                            <div><strong>Element
                                                                    : </strong>${node.html}</div>
                                                        </div>
                                                    </div>
                                                </#list>
                                            <#elseif issuekey=='helpUrl'>
                                                <div><strong>${issuekey?cap_first} : </strong><a
                                                            target="_blank"
                                                            href="${issues[issuekey]}"
                                                            class="alert-link">${issues[issuekey]}</a></div>
                                                <hr/>
                                            <#else>
                                                <div><strong>${issuekey?cap_first} : </strong>${issues[issuekey]}</div>
                                                <hr/>
                                            </#if>
                                        </#list>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    </#list>
                </div>
            </div>
        </#list>
    </div>
    <#include 'footer.ftl'>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
        integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>
</body>
</html>