package com.lutfisobri.soca.utils.exceptions

import com.lutfisobri.soca.data.network.response.BaseResponse

class UnauthorizedException(val response: BaseResponse): Exception(response.message)