const assert = require('assert');
const got = require('got');
const { Given, When, Then } = require('cucumber');

Given('there are scheduled departures from an airport in the next hours', function () {
    this.airportCode = 'AMS'
});

Given('there are flights that already departed in the past hours', function () {
    this.direction = 'D';
});

Given('the past and future windows were not changed', function () {
    pastHours = 4;
    futureHours = 2;
});


When('the client performs the action to display the departures from this airport', async function () {
    this.fids_response = await got.get('http://localhost:3001/flifo/v3/flights/' + this.airportCode + '/' + this.direction);
    this.fids_body = JSON.parse(this.fids_response.body);
    assert(this.fids_body.success);
});


Then('the flights scheduled to depart in the next {string} hours are displayed', function (hours) {
    assert(this.fids_body != null);
    assert(this.fids_body.flightRecord.length == 3);
});


Then('the STD of scheduled flights are present', function () {
    this.fids_body.flightRecord.forEach(function (item) { assert(item.scheduled != null); });
});


Then('the flights that departed in the past {string} hours are displayed', function (hours) {
    this.fids_body.flightRecord.forEach(function (item) {
        var scheduled = new Date(item.scheduled);

        var before = new Date();
        before.setHours(before.getHours()-parseInt(hours));

        assert(before < scheduled);
    });
});
